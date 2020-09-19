package com.concept.test.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.concept.test.data.entity.Employee;

@RepositoryRestResource(collectionResourceRel = "employees", path = "employees")
public interface EmpleadoRepository extends CrudRepository<Employee, String>, JpaSpecificationExecutor<Employee>
{
	List<Employee> findByLastName(String lastName);
}
