package com.concept.bsn.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.concept.bsn.OrderService;
import com.concept.bsn.ProductService;
import com.concept.dto.UsageDto;
import com.concept.test.data.entity.Customer;
import com.concept.test.data.entity.Order;
import com.concept.test.data.entity.Order.Status;
import com.concept.test.data.entity.OrderDetail;
import com.concept.test.data.entity.OrderDetailPK;
import com.concept.test.data.entity.Product;
import com.concept.test.data.repository.OrderRepository;
import com.concept.test.data.repository.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Resource
	private OrderRepository orderRepository;
	
	@Resource
	private ProductService productService;

	@Override
	@Transactional
	public UsageDto recieveOrder(Customer customer, Product product, int quantityOrdered) {
		Product productRecovered = productService.findProduct(product.getProductCode());
		productService.reserveStockProduct(productRecovered, quantityOrdered);
		
		//TODO check why is hibernate doing a unnecessary select in customer 
		Order order = Order.builder()
				.customer(customer)
				.orderDate(LocalDate.now())
				.requiredDate(LocalDate.now().plusDays(5L))
				.status(Order.Status.NEW)
				.build();
		
		OrderDetail orderDetail = OrderDetail.builder()
				.id(OrderDetailPK.builder()
						.product(productRecovered)
						.order(order)
						.build())
				.quantityOrdered(quantityOrdered)
				.priceEach(productRecovered.getBuyPrice())
				.build();
		order.addOrderdetail(orderDetail);
		Order orderSaved = orderRepository.save(order);
		return UsageDto.builder()
				.orderNumber(orderSaved.getOrderNumber())
				.customer(customer.getCustomerNumber())
				.orderDate(orderSaved.getOrderDate())
				.priceEach(productRecovered.getBuyPrice())
				.product(productRecovered.getProductCode())
				.quantity(quantityOrdered)
				.totalQuantity(productRecovered.getBuyPrice().multiply(BigDecimal.valueOf(quantityOrdered)))
				.status(orderSaved.getStatus().name())
				.build();
	}

	@Override
	public List<Order> getOrdersByStatus(Status orderStatus) {
		return orderRepository.findByStatus(orderStatus);
		
	}
	
	
}
