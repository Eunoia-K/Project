package com.tasks.demo;


import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;

import com.sun.istack.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PaymentDto {

	private String id;			//관리번호
	//@NotEmpty(groups = Add.class, message="카드정보는 필수 입력항목입니다.")
	private String cardno;		//카드번호
	//@NotEmpty(groups = Add.class, message="카드정보는 필수 입력항목입니다.")
	private String expiryDate;	//유효기간
	//@NotEmpty(groups = Add.class, message="카드정보는 필수 입력항목입니다.")
	private String cvc;			//cvc
	//@NotEmpty(groups = Add.class, message="카드정보는 필수 입력항목입니다.")
	private Integer discount;	//할부개월
	
	//@NotEmpty(message = "금액은 필수 항목입니다.")
	//@Range(min = 100, max = 1000000000, message="결제 금액은 100원이상 10억원 이하로 가능합니다.", groups = Add.class)
	private Integer amount;		//결제금액
	private Integer vat;		//부가가치세
	private String cardInfo;	//카드정보(암호화)
	private String strdata;		//string데이터(카드사에 전달한)
	private String div;			//구분(결제/결제취소)
	private String paymentId;	//결제관리번호(취소시에만 )

	@Builder
	public PaymentDto(String div, Integer amount, Integer vat, String cardInfo, Integer discount) {
		
		this.div = div;
		this.amount = amount;
		this.vat = vat;
		this.cardInfo = cardInfo;
		this.discount = discount;
	}
	
	public Payments toEntity() {
		return Payments.builder()
				.div(div)
				.amount(amount)
				.vat(vat)
				.cardinfo(cardInfo)
				.discount(discount)
				.paymentid(paymentId)
				.build();
		
	}
	
	

}
