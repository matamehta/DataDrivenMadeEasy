package com.easyautomation.datadrivenmadeeasy.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.easyautomation.datadrivenmadeeasy.exceptions.JSONParseException;
import com.easyautomation.datadrivenmadeeasy.queryattributes.QueryFromJsonFile;

public class AccessJsonFile {
	
	File file;
	JSONParser parser;
	JSONObject jsonObject;
	Object object;
	FileWriter writeFile;
	QueryFromJsonFile jsonQuery;
	AccessJson accessJSON;
	
	public AccessJsonFile(File file) {
		this.file = file;
		parser = new JSONParser();
		jsonQuery = new QueryFromJsonFile();
		accessJSON = new AccessJson();
	}
	
	public Object getPropertyValueFromJSONObject(String key) throws Exception {
		Object value;
		try {
			object = parser.parse(new FileReader(file));
			jsonObject = (JSONObject) object;
			value = accessJSON.getPropertyValueFromJSONObject(jsonObject, key);
		}
		catch(ParseException e) {
			throw new JSONParseException();
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("File " + file + " is not found");
		}
		catch(IOException e) {
			throw new IOException("Could not read file " + file);
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return value;
	}
	
	public int getCountOfObjectsInJSONArray(String key) throws Exception {
		int countOfObjects = -1;
		try {
			object = parser.parse(new FileReader(file));
			jsonObject = (JSONObject) object;
			countOfObjects = accessJSON.getCountOfObjectsInJSONArray(jsonObject, key);
		}
		catch(ParseException e) {
			throw new JSONParseException();
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("File " + file + " is not found");
		}
		catch(IOException e) {
			throw new IOException("Could not read file " + file);
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return countOfObjects;
	}
	
	public JSONObject getFirstObjectFromJSONArray(String key) throws Exception {
		JSONObject jsonObjectToReturn;
		try {
			object = parser.parse(new FileReader(file));
			jsonObject = (JSONObject) object;
			jsonObjectToReturn = accessJSON.getFirstObjectFromJSONArray(jsonObject, key);
		}
		catch(ParseException e) {
			throw new JSONParseException();
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("File " + file + " is not found");
		}
		catch(IOException e) {
			throw new IOException("Could not read file " + file);
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return jsonObjectToReturn;
	}
	
	public Object getFirstValueFromJSONArray(String key) throws Exception {
		Object valueToReturn;
		try {
			object = parser.parse(new FileReader(file));
			jsonObject = (JSONObject) object;
			valueToReturn = accessJSON.getFirstValueFromJSONArray(jsonObject, key);
		}
		catch(ParseException e) {
			throw new JSONParseException();
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("File " + file + " is not found");
		}
		catch(IOException e) {
			throw new IOException("Could not read file " + file);
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return valueToReturn;
	}
	
	public JSONArray getJSONArray(String key) throws Exception {
		JSONArray jsonArray;
		try {
			object = parser.parse(new FileReader(file));
			jsonObject = (JSONObject) object;
			jsonArray = accessJSON.getJSONArray(jsonObject, key);
		}
		catch(ParseException e) {
			throw new JSONParseException();
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("File " + file + " is not found");
		}
		catch(IOException e) {
			throw new IOException("Could not read file " + file);
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return jsonArray;
	}
	
	public List<JSONObject> getAllObjectsFromJSONArray(String key) throws Exception {
		List<JSONObject> listOfJSONObject = new ArrayList<JSONObject>();
		try {
			object = parser.parse(new FileReader(file));
			jsonObject = (JSONObject) object;
			listOfJSONObject = accessJSON.getAllObjectsFromJSONArray(jsonObject, key);
		}
		catch(ParseException e) {
			throw new JSONParseException();
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("File " + file + " is not found");
		}
		catch(IOException e) {
			throw new IOException("Could not read file " + file);
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return listOfJSONObject;
	}
	
	public Object[] getAllValuesFromJSONArray(String key) throws Exception {
		Object[] values;
		try {
			object = parser.parse(new FileReader(file));
			jsonObject = (JSONObject) object;
			values = accessJSON.getAllValuesFromJSONArray(jsonObject, key);
		}
		catch(ParseException e) {
			throw new JSONParseException();
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("File " + file + " is not found");
		}
		catch(IOException e) {
			throw new IOException("Could not read file " + file);
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return values;
	}
	
	public List<JSONObject> getObjectsFromJSONArrayBasedOnCondition(String arrayKey, List<String> condition) throws Exception {
		List<JSONObject> filteredJSONObjects = new ArrayList<JSONObject>();
		try {
			object = parser.parse(new FileReader(file));
			jsonObject = (JSONObject) object;
			filteredJSONObjects = accessJSON.getObjectsFromJSONArrayBasedOnCondition(jsonObject, arrayKey, condition);
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return filteredJSONObjects;
	}
	
	public void addPropertyToJSONObject(String key, String value) throws Exception {
		try {
			object = parser.parse(new FileReader(file));
			jsonObject = (JSONObject) object;
			jsonObject = accessJSON.addPropertyToJSONObject(jsonObject, key, value);
			writeFile = new FileWriter(file);
			writeFile.write(jsonObject.toJSONString());
		}
		catch(ParseException e) {
			throw new JSONParseException();
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("File " + file + " is not found");
		}
		catch(IOException e) {
			throw new IOException("Could not read file " + file);
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		finally {
			if(writeFile != null) {
				writeFile.flush();
			}
		}
	}
	
	public void addValueToJSONArray(String arrayKey, Object value) throws Exception {
		try {
			object = parser.parse(new FileReader(file));
			jsonObject = (JSONObject) object;
			jsonObject = accessJSON.addValueToJSONArray(jsonObject, arrayKey, value);
			writeFile = new FileWriter(file);
			writeFile.write(jsonObject.toJSONString());
		}
		catch(ParseException e) {
			throw new JSONParseException();
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("File " + file + " is not found");
		}
		catch(IOException e) {
			throw new IOException("Could not read file " + file);
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		finally {
			if(writeFile != null) {
				writeFile.flush();
			}
		}
	}
	
	public void addJSONObjectToJSONArray(String arrayKey, JSONObject jsonObj) throws Exception {
		try {
			object = parser.parse(new FileReader(file));
			jsonObject = (JSONObject) object;
			jsonObject = accessJSON.addJSONObjectToJSONArray(jsonObject, arrayKey, jsonObj);
			writeFile = new FileWriter(file);
			writeFile.write(jsonObject.toJSONString());
		}
		catch(ParseException e) {
			throw new JSONParseException();
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("File " + file + " is not found");
		}
		catch(IOException e) {
			throw new IOException("Could not read file " + file);
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		finally {
			if(writeFile != null) {
				writeFile.flush();
			}
		}
	}
	
	public Object executeQuery(String query) throws Exception {
		Object object = null;
		try {
			if(query.matches("SELECT")) {
				object = parser.parse(new FileReader(file));
				jsonObject = (JSONObject) object;
				object = jsonQuery.executeSelectQuery(query, jsonObject);
			}
		}
		catch(ParseException e) {
			throw new JSONParseException();
		}
		catch(FileNotFoundException e) {
			throw new FileNotFoundException("File " + file + " is not found");
		}
		catch(IOException e) {
			throw new IOException("Could not read file " + file);
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return object;
	}
}