package com.easyautomation.datadrivenmadeeasy.exceptions;

public class InvalidSearchOperationException extends Exception {
	
	public InvalidSearchOperationException() {
		this("Search operation is not valid");
	}
	
	public InvalidSearchOperationException(String message) {
		super(message);
	}

}
