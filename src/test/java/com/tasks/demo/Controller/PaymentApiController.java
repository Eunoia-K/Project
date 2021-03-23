package com.tasks.demo.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.tasks.demo.ApiController;
import com.tasks.demo.Service.Pay;


public class PaymentApiController extends TestSupport{
	
	@Mock
    private Pay payService;

    @InjectMocks
    private ApiController apiController;
    
    
	@Test
	public void PaymentApi_test() throws Exception {
		
		this.mockMvc.perform(post("/api/add")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.param("cardno","234234234678")
				.param("expiryDate","0623")
				.param("cvc","739")
				.param("discount","7")
				.param("amount","30000")
				.param("vat","34"))
		.andDo(print())
		.andExpect(status().is(HttpStatus.OK.value()))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.strData").exists());
		
		 

		
	}
	
}
