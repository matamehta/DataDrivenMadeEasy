package com.easyautomation.datadrivenmadeeasy.exceptions;

public class NoSuchJSONArrayException extends Exception{

	public NoSuchJSONArrayException() {
		this("Property with given key is not JSON array");
	}
	
	public NoSuchJSONArrayException(String message) {
		super(message);
	}
	
}
