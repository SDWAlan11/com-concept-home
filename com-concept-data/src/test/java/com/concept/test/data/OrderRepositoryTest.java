package com.concept.test.data;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.concept.test.data.entity.Customer;
import com.concept.test.data.entity.Order;
import com.concept.test.data.entity.OrderDetail;
import com.concept.test.data.entity.OrderDetailPK;
import com.concept.test.data.entity.Product;
import com.concept.test.data.repository.OrderDetailsRepository;
import com.concept.test.data.repository.OrderRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {"classpath:spring/test-spring-context-data.xml"})
@DisplayName("Tests for Order in Data Tier")
public class OrderRepositoryTest 
{
	private static final Logger LOG = LoggerFactory.getLogger(OrderRepositoryTest.class);
	
	@Resource
	private OrderRepository orderRepository;
	
	@Resource
	private OrderDetailsRepository orderDetailsRepository;
	
	@Test
	@Transactional(readOnly = true)
	public void testReadOrderByPk()
	{
		Order order = orderRepository.findById(10100).get();
		LOG.info("Order found : {} ", order);
		Assert.assertNotNull(order);
	}	
	
	@Test
	@Transactional(readOnly = true)
	public void testReadOrderDetailByPk()
	{
		OrderDetail order = orderDetailsRepository.findById(
				OrderDetailPK.builder().order(Order.builder().orderNumber(10100).build())
				.product(Product.builder().productCode("S18_1749").build())
				.build()).get();
		LOG.info("Order found : {} ", order);
		Assert.assertNotNull(order);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testReadByPkGettingOrder()
	{
		OrderDetail order = orderDetailsRepository.findById(
				OrderDetailPK.builder().order(Order.builder().orderNumber(10103).build())
				.product(Product.builder().productCode("S10_1949").build())
				.build()).get();
		
		Assert.assertNotNull(order);
		Assert.assertNotNull(order.getOrder());
		LOG.info("Order Detail found : {} ", order);
		LOG.info("Order Detail - Order found : {} ", order.getOrder());
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testReadByPkError()
	{
		 Assertions.assertThrows(IllegalArgumentException.class, () -> {
			 orderDetailsRepository.findById(
					 OrderDetailPK.builder().build()
				).orElseThrow(IllegalArgumentException::new);
		 });
	}

	@Test
	public void testAddOrder()
	{
		Order order = 
				Order.builder()
				.orderNumber(32386)
				.customer(Customer.builder().customerNumber(121).build())
				.comments("BLE")
				.orderDate(LocalDate.now())
				.requiredDate(LocalDate.now())
				.status(Order.Status.DSP)
				.build();
		
		Order orderRecovered = orderRepository.save(order);
		LOG.info("Order Detail Recovered : {} ", orderRecovered);

	}

	@Test
	public void testAddOrderDetail()
	{
		OrderDetail orderDetail = 
				OrderDetail.builder().id(OrderDetailPK.builder()
						.order(Order.builder().orderNumber(10103).build())
						.product(Product.builder().productCode("S18_1749").build()).build())
				.priceEach(BigDecimal.TEN)
				.quantityOrdered(10)
				.orderLineNumber((short) 1)
				.build();
		OrderDetail orderDetailRecovered = orderDetailsRepository.save(orderDetail);
		LOG.info("Order Detail Recovered : {} ", orderDetailRecovered);

	}
	
	@Test
	public void testAddOrderWithDetail()
	{
		Order order = Order.builder()
				.customer(Customer.builder().customerNumber(121).build())
				.orderDate(LocalDate.now())
				.requiredDate(LocalDate.now().plusDays(5L))
				.status(Order.Status.INP)
				.build();
		
		OrderDetail orderDetail = OrderDetail.builder()
				.id(OrderDetailPK.builder()
						.product(Product.builder().productCode("S18_1749").build())
						.order(order)
						.build())
				.quantityOrdered(5)
				.priceEach(BigDecimal.TEN)
				.build();
		order.addOrderdetail(orderDetail);
		
		Order orderRecovered = orderRepository.save(order);
		Assert.assertNotNull(orderRecovered);
		Assert.assertNotNull(orderRecovered.getOrderdetails());
		Assert.assertEquals("Details size not equals", 1, orderRecovered.getOrderdetails().size());
		LOG.info("Order Recovered : {} ", orderRecovered);
		LOG.info("Order Detail Recovered : {} ", orderRecovered.getOrderdetails().get(0));

	}
}
