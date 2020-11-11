package com.concept.bsn;

import com.concept.test.data.entity.Product;

public interface ProductService {

	Product findProduct(String productCode);
	
	void reserveStockProduct(Product product, int quantity);
		
}
