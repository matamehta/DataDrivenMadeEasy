package com.easyautomation.datadrivenmadeeasy.exceptions;

public class InvalidOpertationException extends Exception{
	
	public InvalidOpertationException() {
		this("Given query operation is not valid");
	}
	
	public InvalidOpertationException(String message) {
		super(message);
	}

}
