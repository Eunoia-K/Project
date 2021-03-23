package com.tasks.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@WebMvcTest(ApiController.class)
public class PaymentApiServiceTest {


	  @Autowired
	  private MockMvc mockMvc;

	  @Autowired
	  private ObjectMapper objectMapper;

	  

	  @Test
	  public void test_post() throws Exception {
		  
		  Object randomObj = new Object() {
	            public final String cardno = "1234556667777";
	            public final String expiryDate = "0356";
	            public final String cvc = "345";
	            public final int discount = 12;
	            public final int amount = 23000;
	            public final int vat = 200000;
	            
	        };

	    String content = objectMapper.writeValueAsString(randomObj);

	    mockMvc.perform(post("/api/add")
	        .content(content)
	        .contentType(MediaType.APPLICATION_JSON)
	        .accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        //.andExpect(MockMvcResultMatchers.content().string("데일의 블로그입니다. dale"))
	        .andDo(print());
	  }
	
	
	
}
