package com.concept.test.data.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.concept.test.data.entity.OrderDetail;
import com.concept.test.data.entity.OrderDetailPK;

public interface OrderDetailsRepository extends CrudRepository<OrderDetail, OrderDetailPK>, JpaSpecificationExecutor<OrderDetail> {

}
