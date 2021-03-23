package com.tasks.demo;




import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;

import com.tasks.demo.Service.Pay;



@RunWith(SpringRunner.class)
@RestClientTest(ApiController.class)
@AutoConfigureMockMvc
public class PaymentApiUnitTest {
	//@Autowired
    //private PaymentRepository paymentRepository;
	
	@Autowired
    private MockMvc mockMvc;
	
	String id = "";

	@Before
    public void setUp() throws Exception {
		
		Payments payment = Payments.builder()
				.div("PAYMENTS")
				.amount(40000)
				.vat(20)
				.cardinfo("2342342342342|sdfsd")
				.discount(12)
				.paymentid("fd")
				.build();
		

		//id = paymentRepository.save(payment).getId();
		
       
    }
	

	@Test
	public void Payment_test() throws Exception {
		
	
		JSONObject obj = new JSONObject();
		obj.put("cardno","1234556667777");
		obj.put("expiryDate","0356");
		obj.put("cvc","345");
		obj.put("discount",12);
		obj.put("amount",23000);
		obj.put("vat",200000);
        
        mockMvc.perform(MockMvcRequestBuilders.post("/api/add")
        		.content(obj.toString())
        		.contentType(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0))
                .andExpect(jsonPath("$.strdata").exists());

	
	}

	
	@Test
	public void GetInfo_test() throws Exception {
		
		id = "202103228869401a9564";
		
		//given(service.getInfo(id)).willReturen(new Payments());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/get/{id}", id))
        .andDo(print())
        .andExpect(status().isOk());
		
		/*
		mvc.perform(MockMvcRequestBuilders.get("/api/get/"+id)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		*/
		
	}
	
	
	
}
