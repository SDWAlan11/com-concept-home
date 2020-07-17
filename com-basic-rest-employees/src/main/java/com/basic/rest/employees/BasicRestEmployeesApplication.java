package com.basic.rest.employees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@EntityScan(basePackages = {"com.concept.test.data.entity"})
@EnableJpaRepositories(basePackages = {"com.concept.test.data.repository"})
public class BasicRestEmployeesApplication {
	public static void main(String[] args) {
		SpringApplication.run(BasicRestEmployeesApplication.class, args);
	}
}
