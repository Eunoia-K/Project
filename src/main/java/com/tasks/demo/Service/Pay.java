package com.tasks.demo.Service;


import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tasks.demo.PaymentDto;
import com.tasks.demo.PaymentRepository;
import com.tasks.demo.Payments;
import com.tasks.demo.Exception.ApiException;
import com.tasks.demo.Exception.ExceptionEnum;
import com.tasks.demo.seed.Seed;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class Pay {
	

	private final PaymentRepository paymentResository;
	
	@Transactional
	public String save(PaymentDto payDto) {
		
		//결제금액
		if(payDto.getAmount()< 100 || payDto.getAmount() > 1000000000)
		{
			throw new ApiException(ExceptionEnum.PAYMENT_01);
		}else if(payDto.getVat()==null || payDto.getVat()>payDto.getAmount()) {
			throw new ApiException(ExceptionEnum.PAYMENT_02);
		}
		
		if(payDto.getCardno().length()<14 && payDto.getCardno().length()>16)
		{
			throw new ApiException(ExceptionEnum.PAYMENT_03);
		}
		
		try {
			SimpleDateFormat  dateFormat = new  SimpleDateFormat("MMyy");
			dateFormat.setLenient(false);
			dateFormat.parse(payDto.getExpiryDate());
		}catch( Exception e){
			throw new ApiException(ExceptionEnum.PAYMENT_03);
		}
		
		
		//카드정보 암호화하기
		String cardinfo = payDto.getCardno()+"|"+payDto.getExpiryDate()+"|"+payDto.getCvc();
		payDto.setCardInfo(Seed.encrypt(cardinfo));
		payDto.setDiv("PAYMENT");
		
		return paymentResository.save(payDto.toEntity()).getId();
	}
	
	public Payments getInfo(String id) {
		return paymentResository.findById(id)
				.orElseThrow(() -> new ApiException(ExceptionEnum.NOTINTO_01));
	}
	
	@Transactional
	public String cancel(PaymentDto payDto) {
		Payments pay = getInfo(payDto.getId());
		if(pay.getAmount()<payDto.getAmount())
		{
			throw new ApiException(ExceptionEnum.CANCEL_01);
		}
	
		payDto.setPaymentId(payDto.getId());
		payDto.setCardInfo(pay.getCardInfo());
		payDto.setDiv("CANCEL");
		payDto.setDiscount(0);
		
		if(payDto.getVat()==null)
		{
			payDto.setVat(pay.getVat());
		}
		
		return paymentResository.save(payDto.toEntity()).getId();
	

	}
	
	
	@Transactional
	public String updateStrData(String id, PaymentDto paymentDto) {
	
		Payments pay = getInfo(id);
		
		String strData = MakeStrData(id,paymentDto);
		paymentResository.updateStrdata(id,strData);

       return strData;
   }
	
	//카드사에 전달한 string 데이터(공통 헤더+데이터)
	public String MakeStrData(String id,PaymentDto payDto) {
		
		StringBuilder sb = new StringBuilder(String.format("%-10s", payDto.getDiv()));
		sb.append(String.format("%-20s", id)); 						 //관리번호
		
		sb.append(String.format("%-20s", DtoNullCheck(payDto.getCardno()))); 		//카드번호
		sb.append(String.format("%02d" , payDto.getDiscount())); 					//할부개월수
		sb.append(String.format("%-4s" , DtoNullCheck(payDto.getExpiryDate()))); 	//카드 유효기간
		sb.append(String.format("%-3s" , DtoNullCheck(payDto.getCvc()))); 		//카드 cvc데이터
		
		sb.append(String.format("%10d"  , payDto.getAmount())); 	//결제/취소 금액
		sb.append(String.format("%010d" , payDto.getVat())); 		//결제/취소 금액의 부가세
		sb.append(String.format("%-20s" , DtoNullCheck(payDto.getPaymentId()))); 	//결제 관리번호(취소시에만)
		sb.append(String.format("%-300s", DtoNullCheck(payDto.getCardInfo()))); 	//카드번호,유호기간,cvc데이터 암호화
		sb.append(String.format("%-47s" , ""));						//예비
		
		String str = sb.toString();
		
		return String.format("%4d", str.length())+str;
	}
	
	public String DtoNullCheck(String str) {
		if(str==null) return "";
		else return str;
	}
	
	
	
	
	
	


}
