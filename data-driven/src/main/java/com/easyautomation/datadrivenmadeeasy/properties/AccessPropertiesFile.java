package com.easyautomation.datadrivenmadeeasy.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.easyautomation.datadrivenmadeeasy.exceptions.NoSuchPropertyException;

public class AccessPropertiesFile {
	
	Properties prop;
	File file;
	FileInputStream input;
	FileOutputStream output;
	
	public AccessPropertiesFile(File file) {
		prop = new Properties();
		this.file = file;
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
			input.close();
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
}
