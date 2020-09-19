package com.concept.batch;

import java.util.Collections;
import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver implements MessageListener{

	private CountDownLatch latch = new CountDownLatch(1);
	
	@Autowired JobLauncher launcher;
	@Autowired Job job;

	public CountDownLatch getLatch() {
		return latch;
	}

	
	public void onMessage(Message message) {
		System.out.println("Received <" + message + ">");
		System.err.println(message.getMessageProperties().getDeliveryTag());
		JobParameters jobParameters = new JobParameters(
				Collections.singletonMap("deliveryTag", new JobParameter(message.getMessageProperties().getDeliveryTag()))
		);
		try {
			launcher.run(job, jobParameters);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		latch.countDown();

	}

}