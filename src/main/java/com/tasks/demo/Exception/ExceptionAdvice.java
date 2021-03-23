package com.tasks.demo.Exception;

import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

	 @ExceptionHandler({ApiException.class})
	    public ResponseEntity<ExceptionEntity> exceptionHandler(HttpServletRequest request, final ApiException e) {
	        //e.printStackTrace();
	        return ResponseEntity
	                .status(200)
	                .body(ExceptionEntity.builder()
	                        .errorCode(e.getError().getCode())
	                        .errorMessage(e.getError().getMessage())
	                        .build());
	    }

	    @ExceptionHandler({RuntimeException.class})
	    public ResponseEntity<ExceptionEntity> exceptionHandler(HttpServletRequest request, final RuntimeException e) {
	        e.printStackTrace();
	        return ResponseEntity
	                //.status(ExceptionEnum.RUNTIME_EXCEPTION.getStatus())
	        		.status(200)
	                .body(ExceptionEntity.builder()
	                        .errorCode(ExceptionEnum.RUNTIME_EXCEPTION.getCode())
	                        .errorMessage(e.getMessage())
	                        .build());
	    }

	    @ExceptionHandler({AccessDeniedException.class})
	    public ResponseEntity<ExceptionEntity> exceptionHandler(HttpServletRequest request, final AccessDeniedException e) {
	        e.printStackTrace();
	        return ResponseEntity
	                //.status(ExceptionEnum.ACCESS_DENIED_EXCEPTION.getStatus())
	        		.status(200)
	        		.body(ExceptionEntity.builder()
	                        .errorCode(ExceptionEnum.ACCESS_DENIED_EXCEPTION.getCode())
	                        .errorMessage(e.getMessage())
	                        .build());
	    }

	    @ExceptionHandler({Exception.class})
	    public ResponseEntity<ExceptionEntity> exceptionHandler(HttpServletRequest request, final Exception e) {
	        e.printStackTrace();
	        return ResponseEntity
	                //.status(ExceptionEnum.INTERNAL_SERVER_ERROR.getStatus())
	        		.status(200)
	        		.body(ExceptionEntity.builder()
	                        .errorCode(ExceptionEnum.INTERNAL_SERVER_ERROR.getCode())
	                        .errorMessage(e.getMessage())
	                        .build());
	    }
}
