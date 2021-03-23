package com.tasks.demo.Service;

import java.util.HashMap;
import com.tasks.demo.Payments;
import com.tasks.demo.seed.Seed;


public class GetPaymentInfo {
	
	private final String id;
	private final String div;
	private final HashMap<String,String> cardinfo = new HashMap<>();		//카드정보
	private final HashMap<String,Integer> moneyinfo = new HashMap<>();		//금액정보


	public GetPaymentInfo(Payments payment) {
		
		this.id  = payment.getId();
		this.div = payment.getDiv();
		this.moneyinfo.put("vat",payment.getVat());
		this.moneyinfo.put("amount", payment.getAmount());
		
		String[] arr = Seed.decrypt(payment.getCardInfo()).split("\\|");	//카드정보복호화
		String cardno = arr[0].substring(0,6);
		cardno += String.format("%"+(arr[0].length()-9)+"s","").replace(" ", "*");
		cardno += arr[0].substring(arr[0].length()-3,arr[0].length());
		
		this.cardinfo.put("cvc", arr[2]);
		this.cardinfo.put("expiryDate", arr[1]);
		this.cardinfo.put("cardno", arr[0]);
	}
	
	public String getId() {
		return id;
	}
	

	public String getDiv() {
		return div;
	}
	
	public HashMap<String,Integer> getMoneyinfo() {
		return moneyinfo;
	}
	
	public HashMap<String,String> getCardinfo() {
		return cardinfo;
	}
	
	

}
