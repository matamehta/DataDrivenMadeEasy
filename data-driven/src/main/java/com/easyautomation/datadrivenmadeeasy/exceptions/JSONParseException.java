package com.easyautomation.datadrivenmadeeasy.exceptions;

public class JSONParseException extends Exception{

	public JSONParseException(){
		this("JSON file is not parsable");
	}

	public JSONParseException(String message) {
		super(message);
	}
}
