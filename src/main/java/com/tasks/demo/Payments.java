package com.tasks.demo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payments {
	
	@Id
	@GeneratedValue(generator = "uniqueId")
	@GenericGenerator(name = "uniqueId", strategy = "com.tasks.demo.UniqueIdGenerator")
	@Column(name = "id", length =20)
	private String id;
	
	@Column(name = "cardinfo", length = 300, nullable = true)
	private String cardInfo;
	
	@Column(name = "discount" ,length = 2, nullable = false)
	private Integer discount;
	
	@Column(name = "amount",nullable = false)
	private Integer amount;
	
	@Column(name = "vat",nullable = false)
	private Integer vat;
	
	@Column(name = "strdata",length = 450, nullable = true)
	private String strdata;
	
	@Column(name = "div", nullable = true)
	private String div;
	
	@Column(name = "paymentid", length =20, nullable = true)
	private String paymentId;
	
	@CreationTimestamp
	@Column(name = "savetime")
	LocalDateTime savetime;
	
    
    @Builder
    public Payments(String div, String cardinfo, Integer discount, Integer amount, Integer vat, String paymentid) {
    	this.div = div;
    	this.cardInfo = cardinfo;
    	this.discount = discount;
    	this.amount = amount;
    	this.vat = vat;
    	this.paymentId = paymentid;
    }



    
    
}

