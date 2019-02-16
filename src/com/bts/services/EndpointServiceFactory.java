package com.bts.services;

import java.io.StringWriter;

import org.codehaus.jackson.map.ObjectMapper;

public class EndpointServiceFactory {
	public Object parseJsonToJava(String jsonObject, Class<Object> className) {
		Object object = null;
		try {
			ObjectMapper jsonObjectMapper = new ObjectMapper();
			object = jsonObjectMapper.readValue(jsonObject, className);
		} catch (Exception e) {
			System.out.println(" JSON Parsing Exception: " + e);
		}
		return object;
	}

	public String parseJavaToJson(Object object) {
		StringWriter jsonObj  = null;
		try {
			ObjectMapper javaObjectMapper = new ObjectMapper();
			jsonObj = new StringWriter();
			javaObjectMapper.writeValue(jsonObj, object);
		} catch (Exception e) {
			System.out.println(" JSON Parsing Exception: " + e);
		}
		return jsonObj.toString();
	}
}
