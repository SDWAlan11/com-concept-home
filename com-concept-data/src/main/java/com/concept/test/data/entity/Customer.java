package com.concept.test.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the customers database table.
 * 
 */
@Entity
@Table(name = "customers")
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "customerNumber")
public class Customer extends Audit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int customerNumber;

	private String addressLine1;

	private String addressLine2;

	private String city;

	private String contactFirstName;

	private String contactLastName;

	private String country;

	private BigDecimal creditLimit;

	private String customerName;

	private String phone;

	private String postalCode;

	private String state;

	// bi-directional many-to-one association to Employee
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "salesRepEmployeeNumber")
	private Employee employee;

	// bi-directional many-to-one association to Order
	@JsonBackReference
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	private List<Order> orders;

	// bi-directional many-to-one association to Payment
	@JsonIgnore
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	private List<Payment> payments;

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setCustomer(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setCustomer(null);

		return order;
	}

	public Payment addPayment(Payment payment) {
		getPayments().add(payment);
		payment.setCustomer(this);

		return payment;
	}

	public Payment removePayment(Payment payment) {
		getPayments().remove(payment);
		payment.setCustomer(null);

		return payment;
	}

}