package com.instagramapi.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.instagramapi.utility.ErrorDetails;

public class GlobleExceptions {

	public GlobleExceptions() {
		// TODO Auto-generated constructor stub
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> UserExceptionHandler(UserException userException , WebRequest request){
		
		ErrorDetails err = new ErrorDetails(userException.getMessage(),request.getDescription(false),LocalDateTime.now());
		
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}
	
	
//	if there is any restriction on user fields like number of charters such then it is used
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception , WebRequest request){
		
		ErrorDetails err = new ErrorDetails(exception.getBindingResult().getFieldError().getDefaultMessage(),"validation error",LocalDateTime.now());
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> OtherExceptionHandler(Exception exception , WebRequest request){
		
		ErrorDetails err = new ErrorDetails(exception.getMessage(),request.getDescription(false),LocalDateTime.now());
		
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
		
	}

}
