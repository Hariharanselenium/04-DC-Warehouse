package com.thinktimetechno.Warehouse.endpoints;

import io.netty.util.internal.ThreadLocalRandom;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.JSONObject;


public class DesignationEndPoints extends BaseEndpoints{


	
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
   
	public Response applicationSalesCreatePayload(String token,String jsonFile)  {

		requestSpecification = getRequestWithJSONHeadersToken(token);
//		requestSpecification =getRequestWithJSONHeaders();
		switch (jsonFile){
			case "Create Designation.json":
				application_ENDPOINT_PATH="designation";
				Isid=true;
			        Isname=true;
				break;
			
		}
		 JSONObject postRequestBody = new JSONObject();
	        postRequestBody.put("name","desig"+ randomNumberAsString);
	       
		result=requestSpecification.body(postRequestBody.toString()).post(getBaseUrl() + application_ENDPOINT_PATH);
		
		if(Isname) {
     		String responseBody = result.getBody().asString();
     		
     		JSONObject jsonObject = new JSONObject(responseBody);
     		Name = jsonObject.getJSONObject("data").getJSONObject("designation").getString("name");

	}
		if(Isid) {
     		String responseBody = result.getBody().asString();
     		
     		JSONObject jsonObject = new JSONObject(responseBody);
     		   id = jsonObject.getJSONObject("data").getJSONObject("designation").getInt("id");

	}
//		result=requestSpecification.post(getBaseUrl() + application_ENDPOINT_PATH);

	    return result;
	
	}
	public Response applicationSalesUpdatePayload(String token,String jsonFile) {
		
//		requestSpecification =getRequestWithJSONHeaders();
		requestSpecification = getRequestWithJSONHeadersToken(token);
		switch (jsonFile){
			case "Update Designation.json":
				application_ENDPOINT_PATH="designation/"+id;
				break;
			
		}
		JSONObject postRequestBody1 = new JSONObject();
        postRequestBody1.put("name", "desig"+ randomNumberAsString1);
       
	result=requestSpecification.body(postRequestBody1.toString()).put(getBaseUrl() + application_ENDPOINT_PATH);
	
     	return result;
	}
	public Response applicationSalesfetchPayload(String token,String name ) {
//		requestSpecification =getRequestWithJSONHeaders();
		requestSpecification = getRequestWithJSONHeadersToken(token);
		switch (name){
		case "Fetch all Designations":
			application_ENDPOINT_PATH="designation/all";
			break;
		
		}
			result=requestSpecification.get(getBaseUrl() + application_ENDPOINT_PATH);
	     	return result;
	
}
	
	public Response applicationSalesDeletePayload(String token,String name ) {
//		requestSpecification =getRequestWithJSONHeaders();
		requestSpecification = getRequestWithJSONHeadersToken(token);
		switch (name){
		case "Delete Designation":
			application_ENDPOINT_PATH="designation/delete";
			String requestBody1 = "{ \"ids\": [ \"" + id + "\" ] }";
			requestSpecification.body(requestBody1);
			break;
		
		
		}
			result=requestSpecification.delete(getBaseUrl() + application_ENDPOINT_PATH);
	     	return result;
	
}
}