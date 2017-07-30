package com.easyautomation.datadrivenmadeeasy.exceptions;

public class InvalidQueryException extends Exception{
	
	public InvalidQueryException() {
		this("Given query is not valid");
	}
	
	public InvalidQueryException(String message) {
		super(message);
	}
}
