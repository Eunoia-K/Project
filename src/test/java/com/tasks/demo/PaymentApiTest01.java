package com.tasks.demo;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tasks.demo.Service.Pay;



@RunWith(SpringRunner.class)
@SpringBootTest(properties= {"spring.config.location=classpath:/application-test.properties"})
//@SpringBootTest
@ContextConfiguration(classes = ApiController.class)
@ActiveProfiles
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class PaymentApiTest01 {

	@Autowired
	private ApiController apiController;
	
	private MockMvc mockMvc;
	
	@Before(value = "")
	public void setUp() throws Exception {
	     mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
	}


	
	@Test
	@Transactional
	public void Payment_test() throws Exception {
		
	
		Object randomObj = new Object() {
            public final String cardno = "1234556667777";
            public final String expiryDate = "0356";
            public final String cvc = "345";
            public final int discount = 12;
            public final int amount = 23000;
            public final int vat = 200000;
            
        };
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(randomObj);
		
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/add/")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8")
				.content(json))
		.andDo(print())
		.andExpect(status().isOk());
		//.andExpect(jsonPath("$.success", is(true)))
		//.andExpect(jsonPath("$.data", is(1)));
		
	}

	/*
	@Test
	public void GetInfo_test() throws Exception {
		
		String id = "202103223113e5f36b86";
		
		mvc.perform(get("/api/get/"+id)).andExpect(status().isOk());
		
	}
	*/
}
