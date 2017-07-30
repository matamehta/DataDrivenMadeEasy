package com.easyautomation.datadrivenmadeeasy.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.easyautomation.datadrivenmadeeasy.exceptions.NoSuchPropertyException;
import com.easyautomation.datadrivenmadeeasy.queryattributes.QueryFromPropertiesFile;

public class AccessPropertiesFile {
	
	Properties prop;
	File file;
	FileInputStream input;
	FileOutputStream output;
	QueryFromPropertiesFile q;
	
	public AccessPropertiesFile(File file) {
		prop = new Properties();
		this.file = file;
		q = new QueryFromPropertiesFile();
	}
	
	public String getAPropertyValue(String property) throws Exception {
		String propertyValue = null;
		try {
			input = new FileInputStream(file);
			prop.load(input);
			if(prop.containsKey(property)) {
				propertyValue = prop.getProperty(property);
			}
			else {
				throw new NoSuchPropertyException();
			}
		}
		catch(NoSuchPropertyException e) {
			throw new NoSuchPropertyException("Given property " + property + " is not present");
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("File " + file + " is not found");
		}
		catch(IOException e) {
			throw new IOException("Could not read file " + file);
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
		finally {
			if(input != null) { 
				input.close();
			}
		}
		
		return propertyValue;
	}
	
	public Map<String, String> readProperties(ArrayList<String> propertiesToFetch) throws Exception {
		Map<String, String> properties = new HashMap<String, String>();
		try {
			input = new FileInputStream(file);
			prop.load(input);
			
			for(String property : propertiesToFetch) {
				if(prop.containsKey(property)) {
					properties.put(property, prop.getProperty(property));
				}
				else {
					throw new NoSuchPropertyException();
				}
			}
		}
		catch(NoSuchPropertyException e) {
			throw new NoSuchPropertyException("All given properties are not present");
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("File " + file + " is not found");
		}
		catch(IOException e) {
			throw new IOException("Could not read file " + file);
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
		finally {
			if(input != null) {
				input.close();
			}
		}
		
		return properties;
	}
	
	public Map<String,String> readAllProperties() throws Exception {
		Map<String, String> properties = new HashMap<String, String>();
		try {
			input = new FileInputStream(file);
			prop.load(input);
			
			Enumeration<?> enuKeys = prop.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = prop.getProperty(key);
				properties.put(key, value);
			}
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("File " + file + " is not found");
		}
		catch(IOException e) {
			throw new IOException("Could not read file " + file);
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
		finally {
			if(input != null) {
				input.close();
			}
		}
		
		return properties;
	}

	public void setAProperty(String property, String propertyValue) throws Exception {
		try {
			input = new FileInputStream(file);
			prop.load(input);
			prop.setProperty(property, propertyValue);
			output = new FileOutputStream(file);
			prop.store(output, "");
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("File " + file + " is not found");
		}
		catch(IOException e) {
			throw new IOException("Could not read file " + file);
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
		finally {
			if(input != null) { 
				input.close();
			}
			if(output != null) { 
				output.close();
			}
		}
	}
	
	public void setAProperty(Map<String,String> property) throws Exception {
		try {
			input = new FileInputStream(file);
			prop.load(input);
			for(Map.Entry<String, String> e : property.entrySet()) {
				prop.setProperty(e.getKey(), e.getValue());
			}
			output = new FileOutputStream(file);
			prop.store(output, "");
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("File " + file + " is not found");
		}
		catch(IOException e) {
			throw new IOException("Could not read file " + file);
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
		finally {
			if(input != null) { 
				input.close();
			}
			if(output != null) { 
				output.close();
			}
		}
	}
	
	public void deleteAProperty(String property) throws Exception {
		try {
			input = new FileInputStream(file);
			prop.load(input);
			if(prop.containsKey(property)) {
				prop.remove(property);
			}
			output = new FileOutputStream(file);
			prop.store(output, "");
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("File " + file + " is not found");
		}
		catch(IOException e) {
			throw new IOException("Could not read file " + file);
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
		finally {
			if(input != null) { 
				input.close();
			}
			if(output != null) { 
				output.close();
			}
		}
	}
	
	public void deleteAllProperties() throws Exception {
		try {
			input = new FileInputStream(file);
			prop.load(input);
			
			Enumeration<?> enuKeys = prop.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				prop.remove(key);
			}
			output = new FileOutputStream(file);
			prop.store(output, "");
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("File " + file + " is not found");
		}
		catch(IOException e) {
			throw new IOException("Could not read file " + file);
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
		finally {
			if(input != null) {
				input.close();
			}
			if(output != null) { 
				output.close();
			}
		}
	}
	
	public Map<String,String> executeQuery(String query) throws Exception {
		String operation = q.getOperationFromQuery(query);
		Map<String,String> properties = new HashMap<String,String>();
		switch(operation) {
			// Select *
			// Select prop1,prop2
			case "Select":
			case "select":
				ArrayList<String> parametersList = q.getAttributesFromSelectQuery(query);
				if(parametersList.size() == 1) {
					if(parametersList.get(0) == "*") {
						properties = readAllProperties();
					}
					else {
						String value = getAPropertyValue(parametersList.get(0));
						properties.put(parametersList.get(0), value);
					}
				}
				else {
					properties = readProperties(parametersList);
				}
				break;
			// Update prop=value
			case "Update":
			case "update":
				Map<String,String> property_Update = q.getPropertyAndValueFromUpdateQuery(query);
				setAProperty(property_Update);
				break;
			// Delete *
			// Delete prop,prop2
			case "Delete":
			case "delete":
				ArrayList<String> propertiesList = q.getAttributesFromDeleteQuery(query);
				if(propertiesList.size() == 1) {
					if(propertiesList.get(0) == "*") {
						deleteAllProperties();
					}
					else {
						deleteAProperty(propertiesList.get(0));
					}
				}
				else {
					for(String p : propertiesList) {
						deleteAProperty(p);
					}
				}
				break;
			// Add Prop=value
			case "Add":
			case "add":
				Map<String,String> property_Add = q.getPropertyAndValueFromAddQuery(query);
				setAProperty(property_Add);
				break;
		}
		
		return properties;
	}
}