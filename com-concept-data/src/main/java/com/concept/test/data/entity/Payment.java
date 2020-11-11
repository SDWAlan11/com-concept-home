package com.concept.test.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the payments database table.
 * 
 */
/**
 * @author daniel.arroyo
 *
 */
@Entity
@Table(name="payments")
@NamedQuery(name="Payment.findAll", query="SELECT p FROM Payment p")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment extends HistoricAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PaymentPK id;

	private BigDecimal amount;

	private LocalDate paymentDate;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="customerNumber",referencedColumnName = "customernumber" ,insertable = false, updatable = false)
	private Customer customer;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status;
	
	public enum Status {
	    OPN,
	    PAY,
	    CAN,
	    REF,
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", amount=" + amount + ", paymentDate=" + paymentDate + ""
				+ ", status=" + status + "]";
	}
	
	
}