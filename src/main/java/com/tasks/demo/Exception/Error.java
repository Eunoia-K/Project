package com.tasks.demo.Exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class Error {
	private HttpStatus status;
    private String message;
    private List<String> errors;
    
    public Error(HttpStatus status, String msg, List<String> errors) {
    	super();
    	this.status = status;
    	this.message = msg;
    	this.errors = errors;
    }
    
    public Error(HttpStatus status, String msg, String error) {
    	super();
    	this.status = status;
    	this.message = msg;
    	this.errors = Arrays.asList(error);
    }
}
