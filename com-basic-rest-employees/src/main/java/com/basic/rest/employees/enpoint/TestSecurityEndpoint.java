package com.basic.rest.employees.enpoint;

import javax.ws.rs.GET;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class TestSecurityEndpoint {

	@GetMapping
	public String hello ()
	{
		return "Hello";
	}
}
