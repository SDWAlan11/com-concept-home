package com.concept.test.data.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.concept.test.data.entity.OrderDetail;
import com.concept.test.data.entity.Payment;
import com.concept.test.data.entity.PaymentPK;

public interface PaymentRepository extends CrudRepository<Payment, PaymentPK>, JpaSpecificationExecutor<OrderDetail> {

}
