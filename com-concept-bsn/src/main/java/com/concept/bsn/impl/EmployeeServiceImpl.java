package com.concept.bsn.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.Resource;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import com.concept.bsn.EmployeeService;
import com.concept.dto.EmployeeDto;
import com.concept.dto.Manager;
import com.concept.dto.exception.TestServiceException;
import com.concept.test.data.entity.Employee;
import com.concept.test.data.repository.EmpleadoRepository;
import com.concept.test.data.repository.ManagerRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Resource
	private EmpleadoRepository empleadoRepository;

	@Resource
	private ManagerRepository managerRepository;

	@Resource
	private Mapper mapper;

	@Override
	public EmployeeDto addEmployee(EmployeeDto employeeDto) {
		try {
			Employee empleado = mapper.map(employeeDto, Employee.class);
			return mapper.map(empleadoRepository.save(empleado), EmployeeDto.class);
		} catch (RuntimeException esbException) {
			throw new TestServiceException(esbException.getMessage(), esbException.getCause());
		}
	}

	@Override
	public void addManager(EmployeeDto employeeDto) {
		try {
			Manager manager = mapper.map(employeeDto, Manager.class);
			//managerRepository.save(manager);
		} catch (RuntimeException esbException) {
			throw new TestServiceException(esbException.getMessage(), esbException.getCause());
		}
	}

	@Override
	public EmployeeDto getEmployeeById(String id) {
		Employee employee = empleadoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not Found Employee"));
		return mapper.map(employee, EmployeeDto.class);
	}
	
	@Override
	public List<EmployeeDto> getEmployees() {
		List<EmployeeDto> employeeList = new ArrayList<EmployeeDto>();
		empleadoRepository.findAll().forEach((employee) -> employeeList.add(mapper.map(employee, EmployeeDto.class)));
		return employeeList;
		
	}

}
