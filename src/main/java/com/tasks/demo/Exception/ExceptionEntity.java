package com.tasks.demo.Exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@ToString
@NoArgsConstructor
public class ExceptionEntity {
	
	private String errorCode;
    private String errorMessage;
    private String status;
    
    @Builder
    public ExceptionEntity(HttpStatus status, String errorCode, String errorMessage){
    	this.status = "FAIL";
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    
}
