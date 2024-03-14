package com.thinktimetechno.Warehouse.endpoints;

import io.netty.util.internal.ThreadLocalRandom;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DepartmentsEndPoints extends BaseEndpoints{


	
	RequestSpecification requestSpecification;
	public Response result;
	boolean Isid=false;
	public static int id ;
	public static String Name;
	private  String application_ENDPOINT_PATH="";
   boolean Isname=false;
   int min = 200;
   int max = 2000;

   int randomNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
   String randomNumberAsString = Integer.toString(randomNumber);
   int mina = 3000;
   int maxa = 5000;

   int randomNumber1 = ThreadLocalRandom.current().nextInt(mina, maxa + 1);
   String randomNumberAsString1= Integer.toString(randomNumber1);
   
	public Response applicationSalesCreatePayload(String token,String jsonFile) throws IOException {

		requestSpecification = getRequestWithJSONHeadersToken(token);
//		requestSpecification =getRequestWithJSONHeaders();
		switch (jsonFile){
			case "Create Department.json":
				application_ENDPOINT_PATH="department";
				Isid=true;
			        Isname=true;
				break;
			
		}
		 JSONObject postRequestBody = new JSONObject();
	        postRequestBody.put("name", randomNumberAsString);
	       
		result=requestSpecification.body(postRequestBody.toString()).post(getBaseUrl() + application_ENDPOINT_PATH);
		
		if(Isname) {
     		String responseBody = result.getBody().asString();
     		
     		JSONObject jsonObject = new JSONObject(responseBody);
     		Name = jsonObject.getJSONObject("data").getJSONObject("department").getString("name");

	}
		if(Isid) {
     		String responseBody = result.getBody().asString();
     		
     		JSONObject jsonObject = new JSONObject(responseBody);
     		   id = jsonObject.getJSONObject("data").getJSONObject("department").getInt("id");

	}
//		result=requestSpecification.post(getBaseUrl() + application_ENDPOINT_PATH);

	    return result;
	
	}
	public Response applicationSalesUpdatePayload(String token,String jsonFile) throws IOException {
		
//		requestSpecification =getRequestWithJSONHeaders();
		requestSpecification = getRequestWithJSONHeadersToken(token);
		switch (jsonFile){
			case "Update Department.json":
				application_ENDPOINT_PATH="department/"+id;
				break;
			
		}
		JSONObject putRequestBody = new JSONObject();
        putRequestBody.put("name", randomNumberAsString1);
       
	result=requestSpecification.body(putRequestBody.toString()).put(getBaseUrl() + application_ENDPOINT_PATH);
	
     	return result;
	}
	public Response applicationSalesfetchPayload(String token,String name ) {
//		requestSpecification =getRequestWithJSONHeaders();
		requestSpecification = getRequestWithJSONHeadersToken(token);
		switch (name){
		case "Fetch all departments":
			application_ENDPOINT_PATH="department/all";
			break;
		
		}
			result=requestSpecification.get(getBaseUrl() + application_ENDPOINT_PATH);
	     	return result;
	
}
	
	public Response applicationSalesDeletePayload(String token,String name ) {
//		requestSpecification =getRequestWithJSONHeaders();
		requestSpecification = getRequestWithJSONHeadersToken(token);
		switch (name){
		case "Delete Department":
			application_ENDPOINT_PATH="department/delete";
			String requestBody1 = "{ \"ids\": [ \"" + id + "\" ] }";
			requestSpecification.body(requestBody1);
			break;
		
		
		}
			result=requestSpecification.delete(getBaseUrl() + application_ENDPOINT_PATH);
	     	return result;
	
}
}