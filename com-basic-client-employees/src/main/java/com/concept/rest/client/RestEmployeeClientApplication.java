package com.concept.rest.client;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.concept.test.data.entity.Employee;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import net.bytebuddy.implementation.bind.ArgumentTypeResolver.ParameterIndexToken;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class RestEmployeeClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestEmployeeClientApplication.class, args);

	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}

@RestController
@RequestMapping("/employeesGateway")
class EmployeesApiGatewayRestcontroller{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getEmployeeNamesFallBack")
	@GetMapping("/names")
	public Collection<String > getEmployeesNames(){
		
		ParameterizedTypeReference<Collection<Employee>> parameterizedTypeReference = new ParameterizedTypeReference<Collection<Employee>>() { };
		ResponseEntity<Collection<Employee>> entity = restTemplate.exchange("http://localhost:8080/employees", HttpMethod.GET, null, parameterizedTypeReference);
		return entity.getBody().stream().map(Employee::getFirstName).collect(Collectors.toList());
	}
	
	public Collection<String> getEmployeeNamesFallBack(){
		return Collections.emptySet();
	}
	
}
