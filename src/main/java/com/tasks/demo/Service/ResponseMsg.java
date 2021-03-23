package com.tasks.demo.Service;

import com.tasks.demo.PaymentDto;


public class ResponseMsg {
	
	private final String id;
	private final String strData;
	

	
	public ResponseMsg(String id,String str) {
		this.id = id;
		this.strData = str;
	}
	
	public String getId() {
		return id;
	}
	
	public String getStrData() {
		return strData;
	}
	
	
	
	
}
