package com.concept.test.data;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.concept.test.data.entity.Product;
import com.concept.test.data.entity.Productline;
import com.concept.test.data.repository.ProductLineRepository;
import com.concept.test.data.repository.ProductRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {"classpath:spring/test-spring-context-data.xml"})
@DisplayName("Tests for Product in Data Tier")
public class ProductRepositoryTest {

	private static final Logger LOG = LoggerFactory.getLogger(ProductRepositoryTest.class);
	
	@Resource
	private ProductRepository productRepository;
	
	@Resource
	private ProductLineRepository productLineRepository;
	
	@Test
	@Transactional(readOnly = true)
	public void testReadByPk()
	{
		Product product = productRepository.findById("S10_4698").get();
		LOG.info("Product found : {} ", product);
		Assert.assertNotNull(product);
	}
	
	@Test
	@Transactional(readOnly = true)
	public void testReadProductLineByPk()
	{
		Productline productLine = productLineRepository.findById("Motorcycles").get();
		LOG.info("Product Line found : {} ", productLine);
		Assert.assertNotNull(productLine);
	}
	
	@Test
	public void testAddProduct()
	{
		Product product = productRepository.save(
				Product.builder()
				.productCode("123456")
				.buyPrice(BigDecimal.TEN)
				.productDescription("BLE")
				.productVendor("ME")
				.msrp(BigDecimal.TEN)
				.productName("LALA")
				.productScale("Ships")
				.quantityInStock((short) 2)
				.productline(Productline.builder().productLine("Ships").build())
				.build());
		
		Assert.assertNotNull(product);
		
	}
	
	@Test
	@Transactional
	public void testAddProductLine()
	{
		Productline product = productLineRepository.save(
				Productline.builder()
				.productLine("Ships")
				.textDescription("LOLO")
				.build()
				);
		
		Assert.assertNotNull(product);
		
	}
}
