package com.concept.test.data.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({ "orderNumber", "comments" })
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "orderNumber")
public class Order extends Audit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int orderNumber;

	@Lob
	private String comments;

	@Basic(optional = false)
	private LocalDate orderDate;

	@Basic(optional = false)
	private LocalDate requiredDate;

	private LocalDate shippedDate;

	@Enumerated(EnumType.STRING)
	@Basic(optional = false)
	private Status status;

	@JsonIgnore
	@Builder.Default
	@OneToMany(mappedBy="order")
	private List<OrderDetail> orderdetails = new ArrayList<OrderDetail>(1);

	//bi-directional many-to-one association to Customer
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="customerNumber")
	private Customer customer;

	@JsonGetter
	public int getCustomerNumber() {
		return this.customer.getCustomerNumber();
	}
	
	public OrderDetail addOrderdetail(OrderDetail orderdetail) {
		getOrderdetails().add(orderdetail);
		orderdetail.setOrder(this);

		return orderdetail;
	}

	public OrderDetail removeOrderdetail(OrderDetail orderdetail) {
		getOrderdetails().remove(orderdetail);
		orderdetail.setOrder(null);

		return orderdetail;
	}


	@Override
	public String toString() {
		return "Order [orderNumber=" + orderNumber + ", comments=" + comments + ", orderDate=" + orderDate
				+ ", requiredDate=" + requiredDate + ", shippedDate=" + shippedDate + ", status=" + status
				 + ", customer=" + customer.getCustomerNumber() + "]";
	}
	
	public enum Status {
		SHP, //Shipped
		RSV, //Resolved
		OHD, //On hold
		DSP, //Diputed
		INP, //In progress
		CNL,  //Canceled
		NEW  //New Order
	}

}