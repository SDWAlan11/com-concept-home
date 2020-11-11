package com.concept.test.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;

import com.concept.test.data.entity.Payment.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The primary key class for the payments database table.
 * 
 */
@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false, fetch = FetchType.LAZY)
	@JoinColumn(name="customerNumber", nullable = false)
	private Customer customer;

	private String checkNumber;
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PaymentPK)) {
			return false;
		}
		PaymentPK castOther = (PaymentPK)other;
		return 
			(this.customer == castOther.customer)
			&& this.checkNumber.equals(castOther.checkNumber);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.customer.getCustomerNumber();
		hash = hash * prime + this.checkNumber.hashCode();
		
		return hash;
	}

	@Override
	public String toString() {
		return "PaymentPK [customer=" + customer.getCustomerNumber() + ", checkNumber=" + checkNumber + "]";
	}
}