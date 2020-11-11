package com.concept.test.data.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the productlines database table.
 * 
 */
@Entity
@Table(name="productlines")
@NamedQuery(name="Productline.findAll", query="SELECT p FROM Productline p")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(callSuper = true)
public class Productline extends Audit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String productLine;

	@Lob
	@ToString.Exclude
	private String htmlDescription;

	@Lob
	@ToString.Exclude
	private byte[] image;

	private String textDescription;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="productline")
	@ToString.Exclude
	private List<Product> products;


	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setProductline(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProductline(null);

		return product;
	}

}