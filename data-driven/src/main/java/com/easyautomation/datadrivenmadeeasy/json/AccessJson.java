package com.easyautomation.datadrivenmadeeasy.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.easyautomation.datadrivenmadeeasy.exceptions.InvalidSearchOperationException;
import com.easyautomation.datadrivenmadeeasy.exceptions.NoSuchJSONArrayException;
import com.easyautomation.datadrivenmadeeasy.exceptions.NoSuchPropertyException;

public class AccessJson {
	
	public Object getPropertyValueFromJSONObject(JSONObject json, String key) throws Exception {
		Object value;
		try {
			if(json.containsKey(key)) {
				value = json.get(key);
			}
			else {
				throw new NoSuchPropertyException();
			}
		}
		catch(NoSuchPropertyException e) {
			throw new NoSuchPropertyException("Property " + key + " is not present in JSON");
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
		
		return value;
	}
	
	public JSONArray getJSONArray(JSONObject json, String arrayKey) throws Exception {
		JSONArray jsonArray;
		try {
			if(json.containsKey(arrayKey)) {
				if(json.get(arrayKey) instanceof JSONArray) {
					jsonArray = (JSONArray) json.get(arrayKey);
				}
				else {
					throw new NoSuchJSONArrayException();
				}
			}
			else {
				throw new NoSuchPropertyException();
			}
		}
		catch(NoSuchPropertyException e) {
			throw new NoSuchPropertyException("Property " + arrayKey + " is not present in JSON");
		}
		catch(NoSuchJSONArrayException e) {
			throw new NoSuchJSONArrayException();
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
		
		return jsonArray;
	}
	
	public List<JSONObject> getAllObjectsFromJSONArray(JSONObject json, String arrayKey) throws Exception {
		List<JSONObject> listOfJSONObject = new ArrayList<JSONObject>();
		try {
			if(json.containsKey(arrayKey)) {
				if(json.get(arrayKey) instanceof JSONArray) {
					JSONArray jsonArray = (JSONArray) json.get(arrayKey);
					JSONObject obj = new JSONObject();
					int count = 0;
					while(count < jsonArray.size()) {
						obj = (JSONObject) jsonArray.get(count);
						listOfJSONObject.add(obj);
						count++;
						
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
		catch(NoSuchPropertyException e) {
			throw new NoSuchPropertyException("Property " + arrayKey + " is not present in JSON");
		}
		catch(NoSuchJSONArrayException e) {
			throw new NoSuchJSONArrayException();
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
		
		return listOfJSONObject;
	}
	
	public List<JSONObject> getAllObjectsFromJSONArray(JSONArray array) throws Exception {
		List<JSONObject> listOfJSONObject = new ArrayList<JSONObject>();
		try {
			JSONObject obj = new JSONObject();
			int count = 0;
			while(count < array.size()) {
				obj = (JSONObject) array.get(count);
				listOfJSONObject.add(obj);
				count++;
				
			}
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
		
		return listOfJSONObject;
	}
	
	public Object[] getAllValuesFromJSONArray(JSONObject json, String arrayKey) throws Exception {
		Object[] values;
		try {
			if(json.containsKey(arrayKey)) {
				if(json.get(arrayKey) instanceof JSONArray) {
					JSONArray jsonArray = (JSONArray) json.get(arrayKey);
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
		catch(NoSuchPropertyException e) {
			throw new NoSuchPropertyException("Property " + arrayKey + " is not present in JSON");
		}
		catch(NoSuchJSONArrayException e) {
			throw new NoSuchJSONArrayException();
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
		
		return values;
	}
	
	public Object[] getAllValuesFromJSONArray(JSONArray array) throws Exception {
		Object[] values;
		try {
			values = array.toArray();	
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
		
		return values;
	}
	
	public List<JSONObject> getObjectsFromJSONArrayBasedOnCondition(JSONObject json, String arrayKey, List<String> condition) throws Exception {
		List<JSONObject> filteredJSONObjects = new ArrayList<JSONObject>();
		try {
			List<JSONObject> jsonObjects = getAllObjectsFromJSONArray(json, arrayKey);
			for(int counter = 0; counter < condition.size(); counter++) {
				jsonObjects = getFilteredJSONObjects(jsonObjects, condition.get(counter));
			}
			filteredJSONObjects = jsonObjects;
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
		
		return filteredJSONObjects;
	}
	
	public List<JSONObject> getObjectsFromJSONArrayBasedOnCondition(JSONArray array, List<String> condition) throws Exception {
		List<JSONObject> filteredJSONObjects = new ArrayList<JSONObject>();
		try {
			List<JSONObject> jsonObjects = getAllObjectsFromJSONArray(array);
			for(int counter = 0; counter < condition.size(); counter++) {
				jsonObjects = getFilteredJSONObjects(jsonObjects, condition.get(counter));
			}
			filteredJSONObjects = jsonObjects;
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
		
		return filteredJSONObjects;
	}
	
	public static List<JSONObject> getFilteredJSONObjects(List<JSONObject> jsonObjects, String condition) throws Exception {
		List<JSONObject> filteredObjects = new ArrayList<JSONObject>();
		try {
			String[] params;
			if(condition.contains(" EQUALS ")) {
				params = condition.trim().split(" EQUALS ");
				for(int counter = 0; counter < jsonObjects.size(); counter++) {
					JSONObject obj = jsonObjects.get(counter);
					if(obj.containsKey(params[0].trim())) {
						if(obj.get(params[0].trim()).equals(params[1].trim())) {
							filteredObjects.add(obj);
						}
					}
				}
			}
			else if(condition.contains(" CONTAINS ")) {
				params = condition.trim().split(" CONTAINS ");
				for(int counter = 0; counter < jsonObjects.size(); counter++) {
					JSONObject obj = jsonObjects.get(counter);
					if(obj.containsKey(params[0].trim())) {
						String key = params[0].trim();
						String valueToSearch = params[1].trim();
						
						Object value = obj.get(key);
						String stringValue = String.valueOf(value);
						if(valueToSearch.contains(stringValue)) {
							filteredObjects.add(obj);
						}
					}
				}
			}
			else {
				throw new InvalidSearchOperationException();
			}
		}
		catch(InvalidSearchOperationException e) {
			throw new InvalidSearchOperationException();
		}
		catch (Exception e) {
			throw new Exception(e.getCause().toString());
		}
		
		return filteredObjects;
	}
}