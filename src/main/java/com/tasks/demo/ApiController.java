package com.tasks.demo;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tasks.demo.Service.GetPaymentInfo;
import com.tasks.demo.Service.Pay;
import com.tasks.demo.Service.ResponseMsg;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private Pay pay;

	//결제API
	//@Validated(Add.class)
	@RequestMapping(value="/add" ,  method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseMsg payment(@Valid @RequestBody PaymentDto payDto) throws Exception{
		
		String id = pay.save(payDto);

		String strData = pay.updateStrData(id,payDto);
		//String strData = "sdfasdf";
		return new ResponseMsg(id,strData);
	}
	
	//결제취소API
	@PostMapping("/cancel")
	public ResponseMsg cancel(@RequestBody PaymentDto payDto) throws Exception {
			
		String id = pay.cancel(payDto);
		String strData = pay.updateStrData(id,payDto);
		//String strData = "sdfasdf";
		return new ResponseMsg(id,strData);
	}
	
	//데이터조회 API
	@GetMapping("/get/{id}")
	public GetPaymentInfo getInfo(@PathVariable(value="id") String id) throws Exception{
		Payments payment = pay.getInfo(id);
		
		return new GetPaymentInfo(payment);
		
	}
	


}
