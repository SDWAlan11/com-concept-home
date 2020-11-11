package com.concept.test.data.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.concept.test.data.entity.Productline;

public interface ProductLineRepository extends CrudRepository<Productline, String>, JpaSpecificationExecutor<Productline> {

}
