package com.concept.batch;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("spring-batch")
public class RestController {

	static final String topicExchangeName = "run-employee-job-exchange";
	static final String queueName = "run-employee-job-queue";

	@Autowired private RabbitTemplate rabbitTemplate;
	@Autowired private Receiver receiver;

	@GetMapping("/sendMessage")
	public String sendMessage() throws InterruptedException {
		rabbitTemplate.convertAndSend(topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
		if(receiver.getLatch().await(10000, TimeUnit.MILLISECONDS)) {
			return "All Good";
		}
		else {
			return "Something wrong";
		}
	}

}
