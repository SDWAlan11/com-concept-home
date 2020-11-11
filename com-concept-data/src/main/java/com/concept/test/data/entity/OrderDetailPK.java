package com.concept.test.data.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The primary key class for the orderdetails database table.
 * 
 */
@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(optional=false, fetch = FetchType.LAZY)
	@JoinColumn(name = "orderNumber", nullable = false)
	private Order order;

	@ManyToOne(optional=false, fetch = FetchType.LAZY)
	@JoinColumn(name = "productCode", nullable = false)
	private Product product;

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrderDetailPK)) {
			return false;
		}
		OrderDetailPK castOther = (OrderDetailPK)other;
		return 
			(this.order == castOther.order)
			&& this.product.equals(castOther.product);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.order.getOrderNumber();
		hash = hash * prime + this.product.getProductCode().hashCode();
		
		return hash;
	}

	@Override
	public String toString() {
		return "OrderDetailPK [order=" + order.getOrderNumber() + ", product=" + product.getProductCode() + "]";
	}
}