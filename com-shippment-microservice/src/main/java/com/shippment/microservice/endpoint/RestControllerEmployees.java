package com.shippment.microservice.endpoint;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.concept.test.data.entity.Employee;
import com.concept.test.data.repository.EmpleadoRepository;

@RestController
@RequestMapping("/v1/employees")
public class RestControllerEmployees {

	@Resource
	private EmpleadoRepository empleadoRepository;
	
	@Resource
	private PasswordEncoder passwordEncoder;
	
	@GetMapping(path = "/{employeeNumber}")
	@Transactional
	public Employee readEmployee(@PathVariable("employeeNumber") int employeeNumber){
		return empleadoRepository.findById(employeeNumber).get();
	}
	
	@PutMapping(path = "/{employeeNumber}/password")
	@Transactional
	public ResponseEntity<String> updateEmployee(@PathVariable("employeeNumber") int employeeNumber, @RequestBody String password){
		Employee employeeToUpdate = empleadoRepository.findById(employeeNumber).get();
		employeeToUpdate.setPassword(passwordEncoder.encode(password));
		empleadoRepository.save(employeeToUpdate);
		return new ResponseEntity<>("Pass Updated", HttpStatus.OK);
	}
	
}
