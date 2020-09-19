package com.concept.batch;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.jsr.ItemProcessListenerAdapter;
import org.springframework.batch.core.jsr.SkipListenerAdapter;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.amqp.AmqpItemWriter;
import org.springframework.batch.item.amqp.builder.AmqpItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.repeat.exception.ExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.Assert;

import com.concept.test.data.entity.Employee;

@Configuration
@EnableBatchProcessing
@EnableBinding(Source.class)
public class BatchConfiguration {
	
	@Autowired
	  public JobBuilderFactory jobBuilderFactory;

	  @Autowired
	  public StepBuilderFactory stepBuilderFactory;
	  
	  @Bean
	  public FlatFileItemReader<Employee> reader() {
	    return new FlatFileItemReaderBuilder<Employee>()
	      .name("employeeItemReader")
	      .resource(new FileSystemResource("C:\\Users\\daniel.arroyo\\IdeaProjects\\com-concept-home\\com-concept-batch\\external\\cvs\\archivoEmpleados.csv"))
	      .delimited()
	      .names(new String[]{"id", "firstName", "lastName", "rol.cveRole"})
	      .fieldSetMapper(new BeanWrapperFieldSetMapper<Employee>() {{
	        setTargetType(Employee.class);
	      }})
	      .build();
	  }
	  
	  @Bean
	  public ItemProcessor<Employee, Employee> getMyProcessor() {
		  ValidatingItemProcessor<Employee> validatingItemProcessor = new ValidatingItemProcessor<>();
		  validatingItemProcessor.setFilter(false);
		  validatingItemProcessor.setValidator( employee -> {
			  if ("AOCD1210".equals(employee.getId())) throw new ValidationException("Alway brokes");
		  });
		  return validatingItemProcessor;
	  }
	  
	  @Bean
	  public SkipListener<Employee, Employee> getSkipListener(){
		  SkipListener<Employee, Employee> skipListener = new SkipListener<Employee, Employee>() {

			@Override
			public void onSkipInRead(Throwable t) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSkipInWrite(Employee item, Throwable t) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSkipInProcess(Employee item, Throwable t) {
				System.out.println(item);
				
			}
			  
		};
		return skipListener;
	  }

	  

//	  @Bean
//	  public AmqpItemWriter<Employee> writer(AmqpTemplate amqpTemplate) {
//	    return new AmqpItemWriterBuilder<Employee>()
//	    		.amqpTemplate(amqpTemplate)
//	      .build();
//	  }
	  
	  
	  
	  @Bean
	  public ItemWriter<Employee> writer(){
		  return new ItemWriter<Employee>() {
			  @Autowired
			  private Source source;
			  
			  @Override
			  public void write(List<? extends Employee> items) throws Exception{
				  
				  items.forEach(item -> {
					  Message<? extends Employee> msg = MessageBuilder.withPayload(item).build();
					  System.err.println(this.source.output().send(msg));
				  });
			  }
		};
	  }
	  
	  
	  @Bean
	  public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
	    return jobBuilderFactory.get("importUserJob")
	      .incrementer(new RunIdIncrementer())
	      .listener(listener)
	      .flow(step1)
	      .end()
	      .build();
	  }

	  @Bean
	  public Step step1(ItemReader<Employee> reader,
			  ItemProcessor<Employee, Employee> validator,
			  ItemWriter<Employee> writer,
			  SkipListener<Employee, Employee> skipListener
			  ) {
	    return stepBuilderFactory.get("step1")
	      .<Employee, Employee> chunk(10)
	      .reader(reader)
	      .processor(validator)
	      .faultTolerant()
	      .skip(ValidationException.class)
	      .skipLimit(1000)
	      .listener(skipListener)
	      .writer(writer)
	      .build();
	  }
}
