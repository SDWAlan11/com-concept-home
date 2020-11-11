package com.concept.test.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product extends Audit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String productCode;

	@Basic(optional = false)
	private BigDecimal buyPrice;

	@Basic(optional = false)
	private BigDecimal msrp;

	@Lob
	@Basic(optional = false)
	private String productDescription;

	@Basic(optional = false)
	private String productName;

	@Basic(optional = false)
	private String productScale;

	@Basic(optional = false)
	private String productVendor;

	@Basic(optional = false)
	private short quantityInStock;

	//bi-directional many-to-one association to Orderdetail
	@OneToMany(mappedBy="product")
	private List<OrderDetail> orderdetails;

	//bi-directional many-to-one association to Productline
	@ManyToOne
	@JoinColumn(name="productLine")
	private Productline productline;

}