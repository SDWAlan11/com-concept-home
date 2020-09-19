package com.basic.rest.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import com.concept.test.data.entity.Employee;
import com.concept.test.data.repository.EmpleadoRepository;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@IntegrationComponentScan
@EnableBinding(Sink.class)
@EntityScan(basePackages = {"com.concept.test.data.entity"})
@EnableJpaRepositories(basePackages = {"com.concept.test.data.repository"})
public class BasicRestEmployeesApplication {
	public static void main(String[] args) {
		SpringApplication.run(BasicRestEmployeesApplication.class, args);
	}
}

@MessageEndpoint
class EmployeeProcessor{
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@ServiceActivator(inputChannel = Sink.INPUT)
	public void acceptNewEmployee(Employee employee) {
		System.err.println(employee);
		empleadoRepository.save(employee);
	}
}
