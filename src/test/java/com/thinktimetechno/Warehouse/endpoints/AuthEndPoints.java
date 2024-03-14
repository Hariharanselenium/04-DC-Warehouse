package com.thinktimetechno.Warehouse.endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AuthEndPoints extends BaseEndpoints{


	
	RequestSpecification requestSpecification;
	public Response result;
	
	private  String application_ENDPOINT_PATH="";
 
	public String applicationSalesCreatePayload(String jsonFile) throws IOException {

		
		requestSpecification =getRequestWithJSONHeaders();
		application_ENDPOINT_PATH="auth/login";
			
				
			
		File jsonDataInFile = new File(System.getProperty("user.dir")+"/src/test/resources/Payloads/AuthPayloads/"+jsonFile);
		result=requestSpecification.body(jsonDataInFile).post(getBaseUrl() + application_ENDPOINT_PATH);

		String responseBody = result.getBody().asString();
	     JSONObject postResponseJson = new JSONObject(responseBody);
	     token = postResponseJson.getJSONObject("data").getString("token");

	     return token;

	
	}
	public void AuthdeletePayload() throws IOException {
		requestSpecification =getRequestWithJSONHeaders();
		application_ENDPOINT_PATH="auth/logout";

		result=requestSpecification.post(getBaseUrl() + application_ENDPOINT_PATH);
	}
	public Response applicationSalesfetchPayload(String name ) {
		requestSpecification =getRequestWithJSONHeaders();
//		requestSpecification = getRequestWithJSONHeadersToken(token);
		switch (name){
		case "Fetch all stations":
			application_ENDPOINT_PATH="stations";
			break;
		
		}
			result=requestSpecification.get(getBaseUrl() + application_ENDPOINT_PATH);
	     	return result;
	
}

	

}