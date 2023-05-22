package com.BikkadIT.CrudOperation.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.BikkadIT.CrudOperation.Payloads.ApiResponce;

/*
 * @author : Rohini
 * @apiNote : This is GlobalException
 * @version : V 2.7.5
 * @Param : Post
 * @see : we have to handle Exception for globally
 */ 

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce> resourceNotFoundException(ResourceNotFoundException ex){
		
		String msg=ex.getMessage();
		
		ApiResponce apiResponce=new ApiResponce(msg,false);
		
		return new ResponseEntity<ApiResponce>(apiResponce,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponce> handleApiException(ApiException ex){
		
		String msg=ex.getMessage();
		
		ApiResponce apiResponce=new ApiResponce(msg,true);
		
		return new ResponseEntity<ApiResponce>(apiResponce,HttpStatus.BAD_REQUEST);
		
	}
	
}
