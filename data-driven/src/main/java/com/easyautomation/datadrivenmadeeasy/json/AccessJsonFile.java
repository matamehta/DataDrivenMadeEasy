package com.easyautomation.datadrivenmadeeasy.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.easyautomation.datadrivenmadeeasy.exceptions.InvalidSearchOperationException;
import com.easyautomation.datadrivenmadeeasy.exceptions.JSONParseException;
import com.easyautomation.datadrivenmadeeasy.exceptions.NoSuchJSONArrayException;
import com.easyautomation.datadrivenmadeeasy.exceptions.NoSuchPropertyException;

public class AccessJsonFile {
	
	File file;
	JSONParser parser;
	JSONObject jsonObject;
	Object object;
	FileWriter writeFile;
	
	public AccessJsonFile(File file) {
		this.file = file;
		parser = new JSONParser();
	}
	
	public Object getPropertyValueFromJSONObject(String key) throws Exception {
		Object value;
		try {
			object = parser.parse(new FileReader(file));
			jsonObject = (JSONObject) object;
			if(jsonObject.containsKey(key)) {
				value = jsonObject.get(key);
			}
			else {
				throw new NoSuchPropertyException();
			}
		}
		catch(ParseException e) {
			throw new JSONParseException();
		}
		catch(NoSuchPropertyException e) {
			throw new NoSuchPropertyException("Property " + key + " is not present in JSON");
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
		
		return value;
	}
	
	public int getCountOfObjectsInJSONArray(String key) throws Exception {
		int countOfArray = -1;
		try {
			object = parser.parse(new FileReader(file));
			jsonObject = (JSONObject) object;
			if(jsonObject.containsKey(key)) {
				if(jsonObject.get(key) instanceof JSONArray) {
					JSONArray jsonArray = (JSONArray) jsonObject.get(key);
					countOfArray = jsonArray.size();
				}
				else {
					throw new NoSuchJSONArrayException();
				}
			}
			else {
				throw new NoSuchPropertyException();
			}
		}
		catch(ParseException e) {
			throw new JSONParseException();
		}
		catch(NoSuchPropertyException e) {
			throw new NoSuchPropertyException("Property " + key + " is not present in JSON");
		}
		catch(NoSuchJSONArrayException e) {
			throw new NoSuchJSONArrayException();
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
		
		return countOfArray;
	}
	
	public JSONObject getFirstObjectFromJSONArray(String key) throws Exception {
		JSONObject jsonObjectToReturn;
		try {
			object = parser.parse(new FileReader(file));
			jsonObject = (JSONObject) object;
			if(jsonObject.containsKey(key)) {
				if(jsonObject.get(key) instanceof JSONArray) {
					JSONArray jsonArray = (JSONArray) jsonObject.get(key);
					jsonObjectToReturn = (JSONObject) jsonArray.get(0);
				}
				else {
					throw new NoSuchJSONArrayException();
				}
			}
			else {
				throw new NoSuchPropertyException();
			}
		}
		catch(ParseException e) {
			throw new JSONParseException();
		}		
		catch(NoSuchPropertyException e) {
			throw new NoSuchPropertyException("Property " + key + " is not present in JSON");
		}
		catch(NoSuchJSONArrayException e) {
			throw new NoSuchJSONArrayException();
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
		
		return jsonObjectToReturn;
	}
	
	public Object getFirstValueFromJSONArray(String key) throws Exception {
		Object valueToReturn;
		try {
			object = parser.parse(new FileReader(file));
			jsonObject = (JSONObject) object;
			if(jsonObject.containsKey(key)) {
				if(jsonObject.get(key) instanceof JSONArray) {
					JSONArray jsonArray = (JSONArray) jsonObject.get(key);
					valueToReturn = jsonArray.get(0);
				}
				else {
					throw new NoSuchJSONArrayException();
				}
			}
			else {
				throw new NoSuchPropertyException();
			}
		}
		catch(ParseException e) {
			throw new JSONParseException();
		}
		catch(NoSuchPropertyException e) {
			throw new NoSuchPropertyException("Property " + key + " is not present in JSON");
		}
		catch(NoSuchJSONArrayException e) {
			throw new NoSuchJSONArrayException();
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
		
		return valueToReturn;
	}
	
	public JSONArray getJSONArray(String key) throws Exception {
		JSONArray jsonArray;
		try {
			object = parser.parse(new FileReader(file));
			jsonObject = (JSONObject) object;
			if(jsonObject.containsKey(key)) {
				if(jsonObject.get(key) instanceof JSONArray) {
					jsonArray = (JSONArray) jsonObject.get(key);
				}
				else {
					throw new NoSuchJSONArrayException();
				}
			}
			else {
				throw new NoSuchPropertyException();
			}
		}
		catch(ParseException e) {
			throw new JSONParseException();
		}
		catch(NoSuchPropertyException e) {
			throw new NoSuchPropertyException("Property " + key + " is not present in JSON");
		}
		catch(NoSuchJSONArrayException e) {
			throw new NoSuchJSONArrayException();
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
		
		return jsonArray;
	}
	
	public List<JSONObject> getAllObjectsFromJSONArray(String key) throws Exception {
		List<JSONObject> listOfJSONObject = new ArrayList<JSONObject>();
		try {
			object = parser.parse(new FileReader(file));
			jsonObject = (JSONObject) object;
			if(jsonObject.containsKey(key)) {
				if(jsonObject.get(key) instanceof JSONArray) {
					JSONArray jsonArray = (JSONArray) jsonObject.get(key);
					Iterator<JSONObject> i = jsonArray.iterator();
					
					while(i.hasNext()) {
						JSONObject object = (JSONObject) i.next();
						listOfJSONObject.add(object);
					}
				}
				else {
					throw new NoSuchJSONArrayException();
				}
			}
			else {
				throw new NoSuchPropertyException();
			}
		}
		catch(ParseException e) {
			throw new JSONParseException();
		}
		catch(NoSuchPropertyException e) {
			throw new NoSuchPropertyException("Property " + key + " is not present in JSON");
		}
		catch(NoSuchJSONArrayException e) {
			throw new NoSuchJSONArrayException();
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
		
		return listOfJSONObject;
	}
	
	public Object[] getAllValuesFromJSONArray(String key) throws Exception {
		Object[] values;
		try {
			object = parser.parse(new FileReader(file));
			jsonObject = (JSONObject) object;
			if(jsonObject.containsKey(key)) {
				if(jsonObject.get(key) instanceof JSONArray) {
					JSONArray jsonArray = (JSONArray) jsonObject.get(key);
					values = jsonArray.toArray();
				}
				else {
					throw new NoSuchJSONArrayException();
				}
			}
			else {
				throw new NoSuchPropertyException();
			}
		}
		catch(ParseException e) {
			throw new JSONParseException();
		}
		catch(NoSuchPropertyException e) {
			throw new NoSuchPropertyException("Property " + key + " is not present in JSON");
		}
		catch(NoSuchJSONArrayException e) {
			throw new NoSuchJSONArrayException();
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
		
		return values;
	}
	
	public List<JSONObject> getObjectsFromJSONArrayBasedOnCondition(String arrayKey, List<String> condition) throws Exception {
		List<JSONObject> filteredJSONObjects = new ArrayList<JSONObject>();
		try {
			List<JSONObject> jsonObjects = getAllObjectsFromJSONArray(arrayKey);
			for(int counter = 0; counter < condition.size(); counter++) {
				jsonObjects = AccessJson.getFilteredJSONObjects(jsonObjects, condition.get(counter));
			}
			filteredJSONObjects = jsonObjects;
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
		
		return filteredJSONObjects;
	}
	
	public void addPropertyToJSONObject(String key, String value) throws Exception {
		try {
			object = parser.parse(new FileReader(file));
			jsonObject = (JSONObject) object;
			jsonObject.put(key, value);
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
			throw new Exception(e.getCause().toString());
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
			if(jsonObject.containsKey(arrayKey)) {
				if(jsonObject.get(arrayKey) instanceof JSONArray) {
					JSONArray list = (JSONArray) jsonObject.get(arrayKey);
					list.add(value);
					jsonObject.put(arrayKey, list);
				}
				else {
					throw new NoSuchJSONArrayException();
				}
			}
			else {
				JSONArray list = new JSONArray();
				list.add(value);
				jsonObject.put(arrayKey, list);
			}
			writeFile = new FileWriter(file);
			writeFile.write(jsonObject.toJSONString());
		}
		catch(ParseException e) {
			throw new JSONParseException();
		}
		catch(NoSuchJSONArrayException e) {
			throw new NoSuchJSONArrayException();
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
			if(writeFile != null) {
				writeFile.flush();
			}
		}
	}
	
	public void addJSONObjectToJSONArray(String arrayKey, JSONObject jsonObj) throws Exception {
		try {
			object = parser.parse(new FileReader(file));
			jsonObject = (JSONObject) object;
			if(jsonObject.containsKey(arrayKey)) {
				if(jsonObject.get(arrayKey) instanceof JSONArray) {
					JSONArray list = (JSONArray) jsonObject.get(arrayKey);
					list.add(jsonObj);
					jsonObject.put(arrayKey, list);
				}
				else {
					throw new NoSuchJSONArrayException();
				}
			}
			else {
				JSONArray list = new JSONArray();
				list.add(jsonObj);
				jsonObject.put(arrayKey, list);
			}
			writeFile = new FileWriter(file);
			writeFile.write(jsonObject.toJSONString());
		}
		catch(ParseException e) {
			throw new JSONParseException();
		}
		catch(NoSuchJSONArrayException e) {
			throw new NoSuchJSONArrayException();
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
			if(writeFile != null) {
				writeFile.flush();
			}
		}
	}
}