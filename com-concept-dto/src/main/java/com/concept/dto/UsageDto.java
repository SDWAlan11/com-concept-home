package com.concept.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsageDto {
	
	private int orderNumber;
	private int customer;
	private String product;
	private BigDecimal priceEach;
	private int quantity;
	private LocalDate orderDate;
	private BigDecimal totalQuantity;
	private String status;
	

}
