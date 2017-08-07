package com.easyautomation.datadrivenmadeeasy.json;

import java.util.ArrayList;
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
			throw new Exception(e.getMessage());
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
			throw new Exception(e.getMessage());
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
			throw new Exception(e.getMessage());
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
			throw new Exception(e.getMessage());
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
			throw new Exception(e.getMessage());
		}
		
		return values;
	}
	
	public Object[] getAllValuesFromJSONArray(JSONArray array) throws Exception {
		Object[] values;
		try {
			values = array.toArray();	
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
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
			throw new Exception(e.getMessage());
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
			throw new Exception(e.getMessage());
		}
		
		return filteredJSONObjects;
	}
	
	public JSONObject addPropertyToJSONObject(JSONObject jsonObject, String key, String value) throws Exception {
		JSONObject json;
		try {
			jsonObject.put(key, value);
			json = jsonObject;
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return json;
	}
	
	public JSONObject addValueToJSONArray(JSONObject jsonObject, String arrayKey, Object value) throws Exception {
		JSONObject json;
		try {
			if(jsonObject.containsKey(arrayKey)) {
				if(jsonObject.get(arrayKey) instanceof JSONArray) {
					JSONArray list = (JSONArray) jsonObject.get(arrayKey);
					list.add(value);
					jsonObject.put(arrayKey, list);
					json = jsonObject;
				}
				else {
					throw new NoSuchJSONArrayException();
				}
			}
			else {
				JSONArray list = new JSONArray();
				list.add(value);
				jsonObject.put(arrayKey, list);
				json = jsonObject;
			}
		}
		catch(NoSuchJSONArrayException e) {
			throw new NoSuchJSONArrayException();
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return json;
	}
	
	public JSONArray addValueToJSONArray(JSONArray jsonArray, Object value) throws Exception {
		JSONArray array;
		try {
			jsonArray.add(value);
			array = jsonArray;
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return array;
	}
	
	public JSONObject addJSONObjectToJSONArray(JSONObject jsonObject, String arrayKey, JSONObject jsonObj) throws Exception {
		JSONObject json;
		try {
			if(jsonObject.containsKey(arrayKey)) {
				if(jsonObject.get(arrayKey) instanceof JSONArray) {
					JSONArray list = (JSONArray) jsonObject.get(arrayKey);
					list.add(jsonObj);
					jsonObject.put(arrayKey, list);
					json = jsonObject;
					
				}
				else {
					throw new NoSuchJSONArrayException();
				}
			}
			else {
				JSONArray list = new JSONArray();
				list.add(jsonObj);
				jsonObject.put(arrayKey, list);
				json = jsonObject;
			}
		}
		catch(NoSuchJSONArrayException e) {
			throw new NoSuchJSONArrayException();
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return json;
	}
	
	public JSONArray addJSONObjectToJSONArray(JSONArray jsonArray,  JSONObject jsonObj) throws Exception {
		JSONArray array;
		try {
			jsonArray.add(jsonObj);
			array = jsonArray;
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return array;
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