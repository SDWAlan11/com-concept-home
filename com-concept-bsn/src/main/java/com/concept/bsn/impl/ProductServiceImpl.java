package com.concept.bsn.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.concept.bsn.ProductService;
import com.concept.bsn.validation.ProductValidation;
import com.concept.bsn.validation.exception.ProductValidationException;
import com.concept.test.data.entity.Product;
import com.concept.test.data.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Resource
	private ProductRepository productRepository;
	
	@Resource 
	private Set<ProductValidation> productValidations;
	
	@Override
	public Product findProduct(String productCode) {
		return productRepository.findById(productCode)
				.orElseThrow(IllegalArgumentException::new);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void reserveStockProduct(Product product, int quantity) {

		int remainingQuantity = product.getQuantityInStock() - quantity;
		product.setQuantityInStock((short)remainingQuantity);
		productValidations.parallelStream().forEach((vallidation) -> vallidation.validate(product));
		productRepository.save(product);
		
	}
	
	@Bean
	public Set<ProductValidation> productValidations(){
		Set<ProductValidation> productValidations = new HashSet<ProductValidation>(2);
		
		productValidations.add((product) -> {
			if (product.getQuantityInStock() < 0)
				throw new ProductValidationException("No more product in Stock");
		});
		
		return productValidations;
	}

}
