package com.easyautomation.datadrivenmadeeasy.exceptions;

public class NoSuchPropertyException extends Exception{
	
	public NoSuchPropertyException() {	
		this("Given property is not present");
	}
	
	public NoSuchPropertyException(String message) {
		super(message);
	}
}
