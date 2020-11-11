package com.concept.bsn.test;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import com.concept.bsn.OrderService;
import com.concept.dto.UsageDto;
import com.concept.test.data.entity.Customer;
import com.concept.test.data.entity.Product;
import com.concept.test.data.repository.OrderRepository;
import com.concept.test.data.repository.ProductRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {"classpath:spring/application-context-esb.xml"})
@DisplayName("Tests for Order Service")
public class TestOrderService {

	@MockBean
	private OrderRepository orderRepository;
	
	@MockBean
	private ProductRepository productRepository;
	
	@Resource
	private OrderService orderService;
	
	private static final Logger LOG = LoggerFactory.getLogger(TestOrderService.class);

	
	@Test
	public void testRecieveOrder() {
		when(productRepository.findById("123456")).thenReturn(Optional.of(Product.builder().productCode("123456").buyPrice(BigDecimal.TEN).build()));
		
		Customer customer = Customer.builder().customerNumber(103).build();
		Product product = Product.builder().productCode("123456").build();
		UsageDto usageDto = orderService.recieveOrder(customer, product, 2);
		Assert.notNull(usageDto, "Usage Null");
		LOG.info("Usage created: {}", usageDto);
	}
	
}
