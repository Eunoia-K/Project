package com.tasks.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class PaymentApiJpaTest {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Before
	public void setup() {
		
	}
	
	//paymentRepository.save() 테스트
	@Test
	public void PaymentRepo_SAVE() {
		 final Payments payments = Payments.builder()
				 .div("PAYMENTS")
				 .amount(3453000)
				 .vat(90)
				 .cardinfo("123456789099878|0932")
				 .discount(3)
				 .paymentid("")
				 .build();
		 
		final Payments savePayment = paymentRepository.save(payments);
		//전달받은 ID값으로 조회 하여 위에 데이터랑 일치하는지 확인
		final Payments findPayment = paymentRepository.findById(savePayment.getId()).get();
		
		assertThat(findPayment.getDiv(),is("PAYMENTS"));
		assertThat(findPayment.getAmount(),is(3453000));
	}
	
	
	


	
	
}
