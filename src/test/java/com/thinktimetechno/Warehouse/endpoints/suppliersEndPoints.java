package com.thinktimetechno.Warehouse.endpoints;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.IOException;

import org.json.JSONObject;

public class suppliersEndPoints extends BaseEndpoints{


		
		RequestSpecification requestSpecification;
		public Response result;
		boolean IsSupplierNumber=false;
		boolean IsSupplierID=false;
		public static int SupplierNumber ;
		public static int SupplierID ;
		private  String application_ENDPOINT_PATH="";

		public Response applicationSalesCreatePayload(String token,String jsonFile) throws IOException {

			
			requestSpecification = getRequestWithJSONHeadersToken(token);
			switch (jsonFile){
			
				case "WAR-29 create supplier.json":
					application_ENDPOINT_PATH="suppliers";
					IsSupplierNumber=true;
					IsSupplierID=true;
					break;
			}
			File jsonDataInFile = new File(System.getProperty("user.dir")+"/src/test/resources/Payloads/suppliers/"+jsonFile);
			result=requestSpecification.body(jsonDataInFile).post(getBaseUrl() + application_ENDPOINT_PATH);
			if(IsSupplierNumber) {
	     		String responseBody = result.getBody().asString();
	     		
	     		JSONObject jsonObject = new JSONObject(responseBody);
	     		SupplierNumber = jsonObject.getJSONObject("data").getJSONObject("supplier").getInt("supplierNumber");

		}
			if(IsSupplierID) {
	     		String responseBody = result.getBody().asString();
	     		
	     		JSONObject jsonObject = new JSONObject(responseBody);
	     		SupplierID = jsonObject.getJSONObject("data").getJSONObject("supplier").getInt("id");

		}
			
			return result;
		}
		public Response applicationSalesUpdatePayload(String token,String jsonFile) throws IOException {
			

			requestSpecification = getRequestWithJSONHeadersToken(token);
			switch (jsonFile){
				case "WAR-33 Update Supplier Details.json":
					application_ENDPOINT_PATH="suppliers/"+SupplierNumber;
					break;
				
			}
			File jsonDataInFile = new File(System.getProperty("user.dir")+"/src/test/resources/Payloads/suppliers/"+jsonFile);
			result=requestSpecification.body(jsonDataInFile).put(getBaseUrl() + application_ENDPOINT_PATH);
	     	return result;
		}
		public Response applicationSalesfetchPayload(String token,String name ) {
			requestSpecification = getRequestWithJSONHeadersToken(token);
			switch (name){
			case "WAR-30 fetch master All zipcodes":
				application_ENDPOINT_PATH="masters/zipcodes/all?page=1&pageSize=20";
				break;
			case "War-30 fetch master all zipcodes by zipcode":
				application_ENDPOINT_PATH="masters/zipcodes/00601";
				break;
			case "WAR-31 get all suppliers":
				application_ENDPOINT_PATH="suppliers/all";
				break;
			case "WAR-32 get supplier by supplier number":
				application_ENDPOINT_PATH="suppliers/"+SupplierNumber;
				break;
			case "WAR-34-35-36 all invoice details of given supplier":
				application_ENDPOINT_PATH="suppliers/invoices?supplierNumber=545&invoiceNumber=";
				break;
			case "WAR-37 fetch master data for supplier by category":
				application_ENDPOINT_PATH="suppliers/master-data?category=vendorType";
				break;
			case "WAR-86 fetch inventory all":
				application_ENDPOINT_PATH="inventory/all?source=PO";
				break;
			case "WAR-115 fetch all items list of given supplierNumber":
				application_ENDPOINT_PATH="suppliers/"+SupplierNumber+"/itemList?description=REDMAN&page=2&active=true&supplierItemNumber";
				break;
			case "get max supplier number":
				application_ENDPOINT_PATH="suppliers/maxNumber";
				break;
			}
			result=requestSpecification.get(getBaseUrl() + application_ENDPOINT_PATH);
	     	return result;
		}
				public Response applicationSalesDeletePayload(String token,String name ) {
					requestSpecification = getRequestWithJSONHeadersToken(token);
					switch (name){
					case "Delete Supplier":
						application_ENDPOINT_PATH="suppliers/delete";
						String requestBody = "{ \"ids\": [ \"" + SupplierID + "\" ] }";
						requestSpecification.body(requestBody);
					
					}
				result=requestSpecification.delete(getBaseUrl() + application_ENDPOINT_PATH);
				
//					result = RestAssured.given()
//						       .multiPart("ids[0]", "546", "text/plain")
//						       .when()
//						        .delete(getBaseUrl() + application_ENDPOINT_PATH);
					
					return result;
		
				}
		
		
	}