package com.tasks.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tasks.demo.Service.Pay;



//@ExtendWith(MockitoExtension.class)

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiController.class)
public class PaymentApiServerTest {

	private Pay payService;
	
	@Mock
	private PaymentRepository paymentRepository;
	
	String id = "";

	
	
	
	
	@Before
	public void setup() throws Exception {
		Payments payment = Payments.builder()
				.div("PAYMENTS")
				.amount(40000)
				.vat(20)
				.cardinfo("2342342342342|sdfsd")
				.discount(12)
				.paymentid("fd")
				.build();
		
		Mockito.when(paymentRepository.save(payment)).thenReturn(payment);
		id = payment.getId();
		payService = new Pay(paymentRepository);
	}
	
	@Test
	@DisplayName("mockito 레파지토리 테스트")
	public void ServerTest() {
		
		
		id = "20180987dfgsdfsdfgsd";
		
		Payments findPayments = payService.getInfo(id);
		
		
		
		/*
		
		Object randomObj = new Object() {
            public final String cardno = "1234556667777";
            public final String expiryDate = "0356";
            public final String cvc = "345";
            public final int discount = 12;
            public final int amount = 23000;
            public final int vat = 200000;
            
        };
        

		 final PaymentDto payDto = new PaymentDto();
		 payDto.setCardno("987654321234");
		 payDto.setExpiryDate("0712");
		 payDto.setCvc("456");
		 payDto.setAmount(70000);
		 payDto.setDiscount(9);
		 payDto.setVat(90);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json;
		try {
			json = objectMapper.writeValueAsString(randomObj);
			 server.expect(MockRestRequestMatchers.anything())
			 .andRespond(MockRestResponseCreators.withSuccess(json, MediaType.APPLICATION_JSON));
			 
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 
		
		 
		 String id = PayService.save(payDto);
		
		
		//assertThat(findPayment.getDiv(),is("PAYMENTS"));
		//assertThat(findPayment.getAmount(),is(3453000));
		*/

		 
				 

	    
		 
	 }


	
}
