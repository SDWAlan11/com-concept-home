package com.concept.bsn;

import java.util.List;

import com.concept.dto.EmployeeDto;


public interface EmployeeService {
	EmployeeDto getEmployeeById(String id);
	List<EmployeeDto> getEmployees();
	EmployeeDto addEmployee(EmployeeDto employeeDto);
	void addManager(EmployeeDto employeeDto);

}
