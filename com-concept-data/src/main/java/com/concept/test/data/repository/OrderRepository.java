package com.concept.test.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.concept.test.data.entity.Order;

public interface OrderRepository 
extends CrudRepository<Order, Integer>, JpaSpecificationExecutor<Order>, PagingAndSortingRepository<Order, Integer> {
	
	List<Order> findByStatus(Order.Status status);
}
