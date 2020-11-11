package com.shippment.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.shippment.microservice", "com.concept.bsn.impl"})
@EntityScan(basePackages = {"com.concept.test.data.entity"})
@EnableJpaRepositories(basePackages = {"com.concept.test.data.repository"})
public class ShippmentApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ShippmentApplication.class, args);
	}
}
