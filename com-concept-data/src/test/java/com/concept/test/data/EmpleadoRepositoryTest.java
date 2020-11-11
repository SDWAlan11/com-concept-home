package com.concept.test.data;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.concept.test.data.entity.Employee;
import com.concept.test.data.entity.Employee.JobTitle;
import com.concept.test.data.entity.Office;
import com.concept.test.data.repository.EmpleadoRepository;
import com.concept.test.data.repository.OfficeRepository;
import com.concept.test.data.repository.spec.SpecEmpleado;
import com.concept.test.data.repository.spec.SpecEmpleadoFirstNameLike;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {"classpath:spring/test-spring-context-data.xml"})
@DisplayName("Tests for Data Tier")
public class EmpleadoRepositoryTest 
{
	private static final Logger LOG = LoggerFactory.getLogger(EmpleadoRepositoryTest.class);
	
	@Resource
	private EmpleadoRepository empleadoRepository;
	
	@Resource
	private OfficeRepository officeRepository;
		
	@Test
	@Transactional(readOnly = true)
	public void testReadByPk()
	{
		Employee empleado = empleadoRepository.findById(1002).orElse(null);
		LOG.info("Empleado Recuperado: {} ", empleado);
		Assert.assertNotNull(empleado);
		LOG.info("Empleado Recuperado: {} ", empleado);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testReadAllEmpleados()
	{
		List<Employee> listEmpleados = empleadoRepository.findAll(new SpecEmpleado());
		for (Employee empleado : listEmpleados) {
			System.out.println(empleado);
		}
	}
	
	@Test
	@Transactional
	public void testCreateUser()
	{
		Employee empleadoCreate = 
				Employee.builder().
				firstName("Alan2")
				.lastName("Arroyo2")
				.extension("1234")
				.email("a@a.com")
				.jobTitle(JobTitle.PRESIDENT)
				.office(Office.builder().officeCode("1").build())
				.build();
		Employee empleadoRecovery = empleadoRepository.save(empleadoCreate);
		empleadoRecovery = empleadoRepository.findById(empleadoCreate.getEmployeeNumber()).orElse(null);
		Assert.assertNotNull(empleadoRecovery);
		LOG.info("Empleado Recuperado: {} ", empleadoRecovery);
		
	}
	
	

	@Test
	@Transactional(readOnly = true)
	public void testSelectEmpleadoCriteria()
	{
		List<Employee> listEmpleados = empleadoRepository.findAll(new SpecEmpleadoFirstNameLike("William"));
		Assert.assertTrue(listEmpleados.size() == 1);
		LOG.info("Emplados Criteria {} ", listEmpleados);
		
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testGetOfficeAndEmployees()
	{
		
		Office officeRecovered = officeRepository.findById("1").orElseThrow(IllegalArgumentException::new);
		LOG.info("Office Recovered: {} ", officeRecovered);
		Assert.assertEquals(6, officeRecovered.getEmployees().size());
		
	}
}
