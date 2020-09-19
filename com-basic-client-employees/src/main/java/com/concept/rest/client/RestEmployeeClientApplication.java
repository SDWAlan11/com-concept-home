package com.concept.rest.client;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.concept.test.data.entity.Employee;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@EnableCircuitBreaker
@EnableHystrix
@EnableZuulProxy
@EnableDiscoveryClient
@EnableBinding(Source.class)
@SpringBootApplication
public class RestEmployeeClientApplication implements CommandLineRunner {

	@Autowired
	private DiscoveryClient discoveryClient;
	
	public static void main(String[] args) {
		SpringApplication.run(RestEmployeeClientApplication.class, args);

	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

	@Override
	public void run(String... args) throws Exception {
		System.err.println(discoveryClient.getServices());
		System.err.println(discoveryClient.getInstances("com-basic-rest-employees"));
		
	}
	
	@RestController
	@RequestMapping("/employeesGateway")
	class EmployeesApiGatewayRestcontroller{
		
		@Autowired
		private RestTemplate restTemplate;
		
		@Autowired
		private Source source;
		
		@HystrixCommand(fallbackMethod = "getEmployeeNamesFallBack")
		@RequestMapping(value = "names" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
		public Collection<String > getEmployeesNames(){
			ParameterizedTypeReference<CollectionModel<Employee>> parameterizedTypeReference = new ParameterizedTypeReference<CollectionModel<Employee>>() { };
			ResponseEntity<CollectionModel<Employee>> entity = restTemplate.exchange("http://com-basic-rest-employees/employees", HttpMethod.GET, null, parameterizedTypeReference);
			return entity.getBody().getContent().
					stream().map(Employee::getFirstName).collect(Collectors.toList());
		}
		
		@PostMapping
		public void writeEmployee(@RequestBody Employee employee) {
			Message<Employee> msg = MessageBuilder.withPayload(employee).build();
			System.err.println(msg);
			System.err.println(this.source.output().send(msg));
		}
		
		public Collection<String> getEmployeeNamesFallBack(){
			return Collections.emptySet();
		}
	}
}
