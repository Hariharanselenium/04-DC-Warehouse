package com.thinktimetechno.Warehouse.endpoints;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EmployeeEndPoints extends BaseEndpoints{


	
	RequestSpecification requestSpecification;
	public Response result;
	public static int applicationNameId;
	public static String examId;
	private  String application_ENDPOINT_PATH="";
   boolean isApplicationId=false;
   int id=5;
	public Response applicationSalesCreatePayload(String token,String jsonFile) throws IOException {


			
				application_ENDPOINT_PATH="employee";
			
		result = RestAssured.given()
		        .header("Authorization", "Bearer " + token) // Added a space after "Bearer"
		        .multiPart("name", "Rahul Kumar", "text/plain")
		        .multiPart("departmentId", "1", "text/plain")
		        .multiPart("roleId", "1", "text/plain")
		        .multiPart("username", "test@123", "text/plain")
		        .multiPart("password", "password", "text/plain")
		        .when()
		        .post(getBaseUrl() + application_ENDPOINT_PATH);

	    return result;
	
	}
	public Response applicationSalesUpdatePayload(String token,String jsonFile) throws IOException {
		

				application_ENDPOINT_PATH="employee/9";
		
		result = RestAssured.given()
		        .header("Authorization", "Bearer " + token) // Added a space after "Bearer"
		        .multiPart("name", "Priya", "text/plain")
		        .multiPart("departmentId", "2", "text/plain")
		        .multiPart("roleId", "6", "text/plain")
		        .when()
		        .put(getBaseUrl() + application_ENDPOINT_PATH);
		 return result;

	}
	public Response applicationSalesfetchPayload(String token,String name ) {
//		requestSpecification =getRequestWithJSONHeaders();
		requestSpecification = getRequestWithJSONHeadersToken(token);
		
	
			application_ENDPOINT_PATH="employee/all";
		
			result=requestSpecification.get(getBaseUrl() + application_ENDPOINT_PATH);
	     	return result;
	
}
	
	public Response applicationSalesDeletePayload(String token,String name ) {
//		requestSpecification =getRequestWithJSONHeaders();
		requestSpecification = getRequestWithJSONHeadersToken(token);
		switch (name){
		case "Delete Employee":
			application_ENDPOINT_PATH="employee/delete";
			String requestBody1 = "{ \"ids\": [ \"" + id + "\" ] }";
			requestSpecification.body(requestBody1);
			break;
		
		
		}
			result=requestSpecification.delete(getBaseUrl() + application_ENDPOINT_PATH);
	     	return result;
	
}
}