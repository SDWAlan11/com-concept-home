package com.shippment.microservice.endpoint;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.concept.bsn.OrderService;
import com.concept.dto.UsageDto;
import com.concept.test.data.entity.Customer;
import com.concept.test.data.entity.Order;
import com.concept.test.data.entity.Product;
import com.concept.test.data.repository.OrderRepository;

@RestController
@CrossOrigin(origins = "localhost:3000")
@RequestMapping("/v1/order")
public class OrderRestEndpoint {
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private OrderRepository orderRepository;
	
	@GetMapping(path = "/{orderNumber}")
	@Transactional
	public Order readOrder(@PathVariable("orderNumber") int orderNumber){
		return orderRepository.findById(orderNumber).get();
				//.orElseThrow(() -> new IllegalArgumentException("Order " + orderNumber + " number not found"));
	}
	
	@GetMapping(path = "/status/{status}")
	@Transactional
	public List<Order> readOrder(@PathVariable("status") String status){
		return orderRepository.findByStatus(Order.Status.valueOf(status));
	}

	@GetMapping(path = "/page/{numberPage}")
	@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.OPTIONS})
	@Transactional
	public List<Order> readAllOrders(@PathVariable("numberPage") int numberPage){
		return orderRepository.findAll(PageRequest.of(numberPage, 20, Sort.by("orderNumber"))).toList();
				//.orElseThrow(() -> new IllegalArgumentException("Order " + orderNumber + " number not found"));
	}

	@PostMapping(path = "/product/{productCode}")
	public UsageDto createOrder(@PathVariable("productCode") String productCode, @QueryParam(value = "quantityOrdered") Integer quantityOrdered, @RequestBody Customer customer) {
		return orderService.recieveOrder(customer, Product.builder().productCode(productCode).build(), quantityOrdered);
	}
}
