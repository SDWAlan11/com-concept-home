package com.concept.test.data.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.concept.test.data.entity.Employee;

public class SpecEmpleadoFirstNameLike extends SpecEmpleado
{
	private String firstName;
	
	public SpecEmpleadoFirstNameLike(String firstName) {
		this.firstName = firstName;
	}


	@Override
	public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery,
			CriteriaBuilder criteriaBuilder) 
	{
		return criteriaBuilder.like(root.<String>get("firstName"), firstName + "%");
	}

}
