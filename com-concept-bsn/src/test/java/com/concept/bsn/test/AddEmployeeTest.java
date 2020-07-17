package com.concept.bsn.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.concept.bsn.EmployeeService;
import com.concept.dto.EmployeeDto;
import com.concept.test.data.entity.Employee;
import com.concept.test.data.entity.Manager;
import com.concept.test.data.repository.EmpleadoRepository;
import com.concept.test.data.repository.ManagerRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {
		"classpath:spring/application-context-esb.xml"
})
public class AddEmployeeTest {
	
	@MockBean
	private EmpleadoRepository empleadoRepository;
	
	@MockBean
	private ManagerRepository managerRepository;

	@Resource
	private EmployeeService employeeService;

	
	@Test
	public void testAddEmployee() {
		
		Employee empleadoTest = new Employee();
		empleadoTest.setId("1234");
		empleadoTest.setFirstName("Alan");
		when(empleadoRepository.save(any(Employee.class))).thenReturn(empleadoTest);
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId("1234");
		employeeDto.setFirstName("Alan");
		employeeDto.setBirthDate(LocalDate.now());
		employeeService.addEmployee(employeeDto);
		
		ArgumentCaptor<Employee> argument = ArgumentCaptor.forClass(Employee.class);
		verify(empleadoRepository).save(argument.capture());
		verify(empleadoRepository, times(1)).save(empleadoTest);
		assertEquals(empleadoTest.getId(), argument.getValue().getId());
		assertEquals(empleadoTest.getFirstName(), argument.getValue().getFirstName());

	}
	
	@Test
	public void testAddEmployeeException() {
		
		Employee empleadoTest = new Employee();
		empleadoTest.setId("1234");
		empleadoTest.setFirstName("Alan");
		when(empleadoRepository.save(any(Employee.class))).thenThrow(new RuntimeException("Something Happen"));
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId("1234");
		
		Assertions.assertThrows(RuntimeException.class, () ->
			employeeService.addEmployee(employeeDto)
		);
	}

	
	
	@Test
	public void testGetEmployeeByIdNotFound() {
		when(empleadoRepository.findById(anyString())).thenReturn(Optional.empty());
		Assertions.assertThrows(NoSuchElementException.class, () ->
			employeeService.getEmployeeById("1234")
		);
	}

}
