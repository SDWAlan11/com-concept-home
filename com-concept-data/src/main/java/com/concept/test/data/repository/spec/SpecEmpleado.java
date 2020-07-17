package com.concept.test.data.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.concept.test.data.entity.Employee;

public class SpecEmpleado implements Specification<Employee> 
{
	
	@Override
	public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery,
			CriteriaBuilder criteriaBuilder) 
	{
		return criteriaQuery.getRestriction();
	}

}
