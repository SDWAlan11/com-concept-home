package com.concept.test.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the orderdetails database table.
 * 
 */
@Entity
@Table(name="orderdetails")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
public class OrderDetail extends Audit implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderDetailPK id;

	private short orderLineNumber;

	private BigDecimal priceEach;

	private int quantityOrdered;

	//bi-directional many-to-one association to Order
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="orderNumber", referencedColumnName = "ordernumber" ,insertable = false, updatable = false)
	@ToString.Exclude
	private Order order;

	//bi-directional many-to-one association to Product
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="productCode",referencedColumnName = "productcode", insertable = false, updatable = false)
	@ToString.Exclude
	private Product product;
	
}