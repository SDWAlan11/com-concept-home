package com.concept.test.data;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.concept.test.data.entity.Customer;
import com.concept.test.data.entity.Payment;
import com.concept.test.data.entity.Payment.Status;
import com.concept.test.data.entity.PaymentPK;
import com.concept.test.data.repository.PaymentRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {"classpath:spring/test-spring-context-data.xml"})
@DisplayName("Tests for Payment in Data Tier")
public class PaymentRepositoryTest 
{
	
	@Resource
	private PaymentRepository paymentRepository;
	
		
	@Test
	@Transactional(readOnly = true)
	public void testReadByPk()
	{
		paymentRepository.findById(
				PaymentPK.builder().customer(Customer.builder().customerNumber(103).build()).checkNumber("HQ336336").build()
				).orElseThrow(IllegalArgumentException::new);
		
	}

	@Test
	public void testAddPayment()
	{
		Payment payment = paymentRepository.save(
				Payment.builder().
				id(
						PaymentPK.builder()
						.customer(Customer.builder().customerNumber(103).build())
						.checkNumber("YQ336336")
						.build()
						)
				.amount(BigDecimal.ZERO)
				.status(Status.PAY)
				.paymentDate(LocalDate.now())
				.build());
		
		Assert.assertNotNull(payment);
		
	}
	
	@Test
	public void testUpdatePayment()
	{
	Payment paymentToUpdate = paymentRepository.findById(
			PaymentPK.builder().customer(Customer.builder().customerNumber(103).build()).checkNumber("JM555205").build()
				).orElseThrow(IllegalArgumentException::new);
	System.err.println(paymentToUpdate);
	paymentToUpdate.setStatus(Status.PAY);
	Payment paymentUpdated = paymentRepository.save(paymentToUpdate);
	System.err.println(paymentUpdated);
	
	Payment paymentRecovered = paymentRepository.findById(
			PaymentPK.builder().customer(Customer.builder().customerNumber(103).build()).checkNumber("JM555205").build()
			).orElseThrow(IllegalArgumentException::new);
	System.err.println(paymentRecovered);
	}
	
	@Ignore
	public void testAddPaymentWithHist() throws IOException
	{
		Payment payment = paymentRepository.save(
				Payment.builder().
				id(
						PaymentPK.builder()
						.customer(Customer.builder().customerNumber(103).build())
						.checkNumber("ZQ336336")
						.build()
						)
				.amount(BigDecimal.ZERO)
				.status(Status.PAY)
				.paymentDate(LocalDate.now())
				.build());
		
		Payment paymentRecovered = paymentRepository.findById(payment.getId()).get();

		paymentRecovered.setStatus(Payment.Status.PAY);
		Payment payment2 = paymentRepository.save(paymentRecovered);
		
		Assert.assertNotNull(payment2);
		
		System.err.println(payment2.getHistoric());
		
		// read from byte array
		ByteArrayInputStream bais = new ByteArrayInputStream(payment2.getHistoric());
		DataInputStream in = new DataInputStream(bais);
		while (in.available() > 0) {
		String element = in.readUTF();
		System.err.println(element);
		}
		
	}
	
}
