package com.concept.test.data;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.concept.test.data.entity.Employee;
import com.concept.test.data.entity.Manager;
import com.concept.test.data.repository.EmpleadoRepository;
import com.concept.test.data.repository.ManagerRepository;
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
	private ManagerRepository managerRepository;
	
	@Test
	@Transactional(readOnly = true)
	public void testReadByPk()
	{
		Employee empleado = empleadoRepository.findById("1234").orElse(null);
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
		Employee empleadoCreate = new Employee();
		empleadoCreate.setFirstName("Alan2");
		empleadoCreate.setLastName("Arroyo2");
		Employee empleadoRecovery = empleadoRepository.save(empleadoCreate);
		empleadoRecovery = empleadoRepository.findById(empleadoCreate.getId()).orElse(null);
		Assert.assertNotNull(empleadoRecovery);
		LOG.info("Empleado Recuperado: {} ", empleadoRecovery);
		
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void testCreateUser2()
	{
		Employee empleadoCreate1 = new Employee();
		empleadoCreate1.setId("4567");
		empleadoCreate1.setFirstName("Alan2");
		empleadoCreate1.setLastName("Arroyo2");
		empleadoCreate1.setBirthDate(LocalDate.now());
		empleadoRepository.save(empleadoCreate1);
		Employee empleadoCreate2 = new Employee();
		empleadoCreate2.setId("4568");
		empleadoCreate2.setFirstName("Alan3");
		empleadoCreate2.setLastName("Prroyo3");
		empleadoCreate2.setBirthDate(LocalDate.now());
		empleadoRepository.save(empleadoCreate2);
		Employee empleadoRecovery1 = empleadoRepository.findById(empleadoCreate1.getId()).orElse(null); 
		Employee empleadoRecovery2 = empleadoRepository.findById(empleadoCreate2.getId()).orElse(null);
		Employee empleadoRecovery3 = empleadoRepository.findById("3").orElse(null);
		LOG.info("Empleado Recuperado: {} ", empleadoRecovery1);
		LOG.info("Empleado Recuperado: {} ", empleadoRecovery2);
		LOG.info("Empleado Recuperado: {} ", empleadoRecovery3);
		System.err.println(empleadoRecovery1);
		
	}
	
	@Test
	@Transactional
	public void testSelectManager()
	{
		Manager manager = managerRepository.findById("1111").orElse(null);
		Assert.assertNotNull(manager);
		Assert.assertEquals( 1, manager.getEmpleadosACargo().size());
		System.out.println("Aqui" + manager.getEmpleadosACargo());
	}
	
	@Test
	@Transactional
	public void testSelectEmpleadoCriteria()
	{
		List<Employee> listEmpleados = empleadoRepository.findAll(new SpecEmpleadoFirstNameLike("Alan"));
		System.err.println("Aqui" + listEmpleados);
		
	}
}
