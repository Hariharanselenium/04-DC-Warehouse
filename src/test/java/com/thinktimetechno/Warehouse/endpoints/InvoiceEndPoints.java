package com.thinktimetechno.Warehouse.endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

public class InvoiceEndPoints extends BaseEndpoints{


	
	RequestSpecification requestSpecification;
	public Response result;
	private  String application_ENDPOINT_PATH="";

	public Response applicationSalesfetchPayload(String token,String jsonFile) throws IOException {

		
		requestSpecification = getRequestWithJSONHeadersToken(token);
		switch (jsonFile){
			case "war-210 download invoice pdf":
				application_ENDPOINT_PATH="sales/invoices/0101142-138/download-pdf";
				break;
			
			
				
		}
		result=requestSpecification.get(getBaseUrl() + application_ENDPOINT_PATH);
     	return result;
	

	}

	
	
	
}