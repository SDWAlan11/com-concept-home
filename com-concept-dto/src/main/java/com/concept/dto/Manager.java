package com.concept.dto;

import java.util.ArrayList;
import java.util.List;

public class Manager extends EmployeeDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7373270992801721528L;

	private List<String> employeesInCharge = new ArrayList<>();

	public List<String> getEmployeesInCharge() {
		return employeesInCharge;
	}

	public void setEmployeesInCharge(List<String> employeesInCharge) {
		this.employeesInCharge = employeesInCharge;
	}
	
	
}
