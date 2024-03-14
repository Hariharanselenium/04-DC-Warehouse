package com.thinktimetechno.Warehouse.endpoints;

import io.netty.util.internal.ThreadLocalRandom;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class PurchaseOrdersEndPoints extends BaseEndpoints{


	
	RequestSpecification requestSpecification;
	public Response result;
	public static int applicationNameId;
	public static String PurchaseOrderNumber;;
	private  String application_ENDPOINT_PATH="";
   boolean IspurchaseOrderNumber=false;
   int min = 200;
   int max = 2000;

   int randomNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
   String randomNumberAsString = Integer.toString(randomNumber);
   
   LocalDate currentDate = LocalDate.now();
   String formattedDate = currentDate.format(DateTimeFormatter.ISO_DATE);
	public Response applicationSalesCreatePayload(String token,String jsonFile) throws IOException {

		
		requestSpecification = getRequestWithJSONHeadersToken(token);
		switch (jsonFile){
		
	
			case "WAR 112 Create Purchase Order.json":
				application_ENDPOINT_PATH="purchase-orders";
				
				String requestBody = "{\"invNumber\": \"+randomNumberAsString+\"}";
				requestSpecification.body(requestBody);
				IspurchaseOrderNumber=true;
				break;

			case "WAR 166 received purchase order.json":
				application_ENDPOINT_PATH="purchase-orders/"+PurchaseOrderNumber+"/received";
				break;
			case "WAR 274 PO open.json":     
				application_ENDPOINT_PATH="purchase-orders/"+PurchaseOrderNumber+"/po-open";
				break;
			
			
				
		}
		File jsonDataInFile = new File(System.getProperty("user.dir")+"/src/test/resources/Payloads/PurchaseOrdersPayloads/"+jsonFile);
		result=requestSpecification.body(jsonDataInFile).post(getBaseUrl() + application_ENDPOINT_PATH);
		if(IspurchaseOrderNumber) {
     		String responseBody = result.getBody().asString();
     		
     		JSONObject jsonObject = new JSONObject(responseBody);
     		PurchaseOrderNumber = jsonObject.getJSONObject("data").getJSONObject("purchaseOrder").getString("purchaseOrderNumber");
//     		JSONArray Items = purchaseOrder.getJSONArray("items");
//     		 if (Items.length() > 0) {
//                 JSONObject firstItems = Items.getJSONObject(0);
//                 PurchaseOrderNumber = firstItems.getString("purchaseOrderNumber");
//             }
	}
	
	
	    return result;
	
	}
	public Response applicationSalesUpdatePayload(String token,String jsonFile) throws IOException {
		
//		requestSpecification =getRequestWithJSONHeaders();
	requestSpecification = getRequestWithJSONHeadersToken(token);
		switch (jsonFile){
		
			case "WAR 116 update prachase order.json":
				application_ENDPOINT_PATH="purchase-orders/"+PurchaseOrderNumber;
				break;
		

		}
		File jsonDataInFile = new File(System.getProperty("user.dir")+"/src/test/resources/Payloads/PurchaseOrdersPayloads/"+jsonFile);
		result=requestSpecification.body(jsonDataInFile).put(getBaseUrl() + application_ENDPOINT_PATH);
     	return result;
	}
	public Response applicationSalesfetchPayload(String token,String name ) {
//		requestSpecification =getRequestWithJSONHeaders();
		requestSpecification = getRequestWithJSONHeadersToken(token);
		switch (name){
		case "WAR-113 fetch Purchase Order List":
			application_ENDPOINT_PATH="purchase-orders/all?sort=desc&orderBy=purchaseOrderNumber";
		
			break;
		case "WAR-79 fetch purchase order lineitems by PO number":
			application_ENDPOINT_PATH="purchase-orders/"+PurchaseOrderNumber;
			IspurchaseOrderNumber=false;
			break;
		case "war-165 fetch max purchase order number":
			application_ENDPOINT_PATH="purchase-orders/max-number?receivedDate="+formattedDate;
			break;
		case "download PO pdf":       
			application_ENDPOINT_PATH="purchase-orders/"+PurchaseOrderNumber+"/download-pdf";
//			application_ENDPOINT_PATH="purchase-orders/06152311-8/download-pdf";
			break;

		}
		result=requestSpecification.get(getBaseUrl() + application_ENDPOINT_PATH);

			
	     	return result;
	
}
	
	public Response applicationSalesDeletePayload(String token,String name ) {
		
//		requestSpecification =getRequestWithJSONHeaders();
requestSpecification = getRequestWithJSONHeadersToken(token);
		switch (name){
		case "delete purchase order":
			application_ENDPOINT_PATH="purchase-orders/delete";
			String requestBody = "{ \"ids\": [ \"" + PurchaseOrderNumber + "\" ] }";
			requestSpecification.body(requestBody);
			break;
	
		
		
		}
			result=requestSpecification.delete(getBaseUrl() + application_ENDPOINT_PATH);
	     	return result;
	
}
}