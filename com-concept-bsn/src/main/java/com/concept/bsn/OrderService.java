package com.concept.bsn;

import java.util.List;

import com.concept.dto.UsageDto;
import com.concept.test.data.entity.Customer;
import com.concept.test.data.entity.Order;
import com.concept.test.data.entity.Product;

public interface OrderService {
	UsageDto recieveOrder(Customer customer, Product product, int quantityOrdered);
	List<Order> getOrdersByStatus(Order.Status orderStatus);
}
