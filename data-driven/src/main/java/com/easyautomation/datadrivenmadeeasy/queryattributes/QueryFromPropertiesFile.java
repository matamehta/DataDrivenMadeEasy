package com.easyautomation.datadrivenmadeeasy.queryattributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.easyautomation.datadrivenmadeeasy.exceptions.InvalidOpertationException;
import com.easyautomation.datadrivenmadeeasy.exceptions.InvalidQueryException;

public class QueryFromPropertiesFile {
	
	public String getOperationFromQuery(String query) throws Exception {
		String task = null;
		
		String[] queryText = query.split(" ");
		try {
			if(query.trim().startsWith("Select") || query.trim().startsWith("start")) {
				task = queryText[0];
			}
			else if(query.trim().startsWith("Update") || query.trim().startsWith("update")) {
				task = queryText[0];
			}
			else if(query.trim().startsWith("Delete") || query.trim().startsWith("delete")) {
				task = queryText[0];
			}
			else if(query.trim().startsWith("Add") || query.trim().startsWith("add")) {
				task = queryText[0];
			}
			else {
				throw new InvalidOpertationException();
			}
		}
		catch(InvalidOpertationException e) {
			throw new InvalidOpertationException("Operation in query " + query + " is not valid");
		}
		catch (Exception e) {
			throw new Exception(e.getCause().toString());
		}
		
		return task;
	}
	
	public ArrayList<String> getAttributesFromSelectQuery(String query) throws Exception {
		ArrayList<String> parametersList = new ArrayList<String>();
		String[] queryParts = query.trim().split(" ");
		try {
			if(queryParts.length == 2) {
				switch(queryParts[1]) {
					case "*":
						parametersList.add("*");
						break;
					default:
						String[] parameters;
						if(queryParts[1].contains(",")){
							parameters = queryParts[1].split(",");
							for(String parameter : parameters) {
								parametersList.add(parameter);
							}
						}
						else {
							parametersList.add(queryParts[1]);
						}
						break;
				}
			}
			else {
				throw new InvalidQueryException();
			}
		}
		catch(InvalidQueryException e) {
			throw new InvalidQueryException("Query " + query + " is not valid");
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
		
		return parametersList;
	}
	
	public ArrayList<String> getAttributesFromDeleteQuery(String query) throws Exception {
		ArrayList<String> parametersList = new ArrayList<String>();
		String[] queryParts = query.trim().split(" ");
		try {
			if(queryParts.length == 2) {
				switch(queryParts[1]) {
					case "*":
						parametersList.add("*");
						break;
					default:
						String[] parameters;
						if(queryParts[1].contains(",")){
							parameters = queryParts[1].split(",");
							for(String parameter : parameters) {
								parametersList.add(parameter);
							}
						}
						else {
							parametersList.add(queryParts[1]);
						}
						break;
				}
			}
			else {
				throw new InvalidQueryException();
			}
		}
		catch(InvalidQueryException e) {
			throw new InvalidQueryException("Query " + query + " is not valid");
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
		
		return parametersList;
	}
	
	public Map<String,String> getPropertyAndValueFromUpdateQuery(String query) throws Exception {
		Map<String,String> properties = new HashMap<String,String>();
		
		String[] queryParts = query.split(" ");
		try {
			if(queryParts.length == 2) {
				String[] propertyValue = queryParts[1].trim().split("=");
				properties.put(propertyValue[0], propertyValue[1]);
			}
			else {
				throw new InvalidQueryException();
			}
		}
		catch(InvalidQueryException e) {
			throw new InvalidQueryException("Query " + query + " is not valid");
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
		
		return properties;
	}
	
	public Map<String,String> getPropertyAndValueFromAddQuery(String query) throws Exception {
		Map<String,String> properties = new HashMap<String,String>();
		
		String[] queryParts = query.split(" ");
		try {
			if(queryParts.length == 2) {
				String[] propertyValue = queryParts[1].trim().split("=");
				properties.put(propertyValue[0], propertyValue[1]);
			}
			else {
				throw new InvalidQueryException();
			}
		}
		catch(InvalidQueryException e) {
			throw new InvalidQueryException("Query " + query + " is not valid");
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
		
		return properties;
	}
	
}
