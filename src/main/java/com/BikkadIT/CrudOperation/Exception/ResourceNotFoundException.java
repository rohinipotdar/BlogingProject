package com.BikkadIT.CrudOperation.Exception;

/*
 * @author : Rohini
 * @apiNote : This is ExceptionClass extends RuntimeException
 * @version : V 2.7.5
 * @Param : Post
 * @see : we have to handle ResourceNotFoundException
 */ 

public class ResourceNotFoundException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;

	static String resouceName;
	
	static String fieldName;
	
	static Long fieldValue;
	
	public ResourceNotFoundException(String resouceName,String fieldName,Long fieldValue ) {
		super(String.format("%s not found with %s %s",resouceName,fieldName, fieldValue));
		this.resouceName=resouceName;
		this.fieldName=fieldName;
		this.fieldValue=fieldValue;
		
	}

	public ResourceNotFoundException(String resouceName2, String fieldName2, String i) {
		super(String.format("%s not found with %s %s",resouceName,fieldName, fieldValue));
		this.resouceName=resouceName;
		this.fieldName=fieldName;
		this.fieldValue=fieldValue;
	}
	
}
