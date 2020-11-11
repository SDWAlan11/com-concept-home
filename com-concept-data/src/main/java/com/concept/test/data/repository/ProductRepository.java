package com.concept.test.data.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.concept.test.data.entity.OrderDetail;
import com.concept.test.data.entity.Product;

public interface ProductRepository extends CrudRepository<Product, String>, JpaSpecificationExecutor<Product> {

}
