package com.thinktimetechno.Warehouse.endpoints;

import java.io.File;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonObject;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CustomerEndPoints extends BaseEndpoints{


	
	RequestSpecification requestSpecification;
	public Response result;
	public static int applicationNameId;
	public static int customerNumber;
	public static String InvoiceNumber;
	
	private  String application_ENDPOINT_PATH="";
   boolean iscustomerNumber=false;
    boolean IsinvoiceNumber=false;
	public Response applicationSalesCreatePayload(String token, String jsonFile) throws IOException {

		requestSpecification = getRequestWithJSONHeadersToken(token);
//		requestSpecification =getRequestWithJSONHeaders();
		switch (jsonFile){
			case "WAR 18 create customer.json":
				application_ENDPOINT_PATH="customers";
				iscustomerNumber=true;
				break;
			
			case "WAR 160 create invoice.json":
				application_ENDPOINT_PATH="sales/invoice";
				break;
			case "change status posted invoice.json":
				application_ENDPOINT_PATH="sales/invoices/"+InvoiceNumber+"/change-status";
				break;
			case "WAR 234 Accept the payments anywhere.json":
				application_ENDPOINT_PATH="customers/"+customerNumber+"/payInvoices";
				break;
			case "WAR 292 create special price for customer.json":
				application_ENDPOINT_PATH="customers/"+customerNumber+"/specialPrice";
				break;
			
			
				
		}
		File jsonDataInFile = new File(System.getProperty("user.dir")+"/src/test/resources/Payloads/CustomerPayloads/"+jsonFile);
		result=requestSpecification.body(jsonDataInFile).post(getBaseUrl() + application_ENDPOINT_PATH);
		if(iscustomerNumber) {
     		String responseBody = result.getBody().asString();
     		
     		JSONObject jsonObject = new JSONObject(responseBody);
     		customerNumber = jsonObject.getJSONObject("data").getJSONObject("customer").getInt("customerNumber");

	}
	    return result;
	
	}
	public Response applicationSalesUpdatePayload(String token,String jsonFile) throws IOException {
		
//		requestSpecification =getRequestWithJSONHeaders();
		requestSpecification = getRequestWithJSONHeadersToken(token);
		switch (jsonFile){
			case "WAR 21 Update Customer Basic Info.json":
				application_ENDPOINT_PATH="customers/"+customerNumber+"/basicInfo";
				break;
			case "WAR 22 Update Customers Classification.json":
				application_ENDPOINT_PATH="customers/"+customerNumber+"/classification";
				break;
			case "WAR 11 Update Customers BillingInfo.json":
				application_ENDPOINT_PATH="customers/"+customerNumber+"/billinginfo";
				iscustomerNumber=false;
				break;
			case "WAR 162 update invoice.json":
				application_ENDPOINT_PATH="sales/invoices/"+InvoiceNumber;
				break;
		}
		File jsonDataInFile = new File(System.getProperty("user.dir")+"/src/test/resources/Payloads/CustomerPayloads/"+jsonFile);
		result=requestSpecification.body(jsonDataInFile).put(getBaseUrl() + application_ENDPOINT_PATH);
	
		return result;
	}
	public Response applicationSalesfetchPayload(String token,String name ) {
//		requestSpecification =getRequestWithJSONHeaders();
	  requestSpecification = getRequestWithJSONHeadersToken(token);
		switch (name){
		case "WAR-19 get all customers":
			application_ENDPOINT_PATH="customers/all?phone=&customerNumber=&city=&company=&name=&dateCreated=&license=&search=";
			
			break;
		case "WAR-20 get customer by customer number":
			application_ENDPOINT_PATH="customers/"+customerNumber;
			break;
		case "WAR-23 get max customer number":
			application_ENDPOINT_PATH="customers/maxNumber";
			
			break;
		case "WAR-24  Customers Master Data All":
			application_ENDPOINT_PATH="customers/master-data/all";
			break;
		case "WAR-13 Customers Master Data  By Category":
			application_ENDPOINT_PATH="customers/master-data?category=zone";
			break;
		case "WAR-10 Get Mobile Providers":
			application_ENDPOINT_PATH="masters/mobile-providers";
			break;
		case "WAR-12 Get State List":
			application_ENDPOINT_PATH="masters/states";
			break;
		case "WAR-14 Get drivers list":
			application_ENDPOINT_PATH="users/drivers";
			break;
		case "WAR-15 Get salesman list":
			application_ENDPOINT_PATH="users/salesman";
			break;
		case "WAR-27 Get payments of customer":
			application_ENDPOINT_PATH="sales/payments?customerNumber=1000&salesman=Administrator";
			break;
		case "WAR-26 fetch invoices for a given customer":
			application_ENDPOINT_PATH="sales/invoices";
			IsinvoiceNumber=true;
			break;
		case "WAR-28 fetch invoices for a given invoice number":
			application_ENDPOINT_PATH="sales/invoices/"+InvoiceNumber;
			break;
		case "WAR-76 Customer Account balancd history":
			application_ENDPOINT_PATH="customers/100/balanceHistory";
			break;
		case "WAR-161 fetch max invoice number":
			application_ENDPOINT_PATH="sales/maxNumber";
			break;
		}
		
			result=requestSpecification.get(getBaseUrl() + application_ENDPOINT_PATH);

//			if(iscustomerNumber) {
//	     		String responseBody = result.getBody().asString();
//	     		
//	     		JSONObject jsonObject = new JSONObject(responseBody);
//	            JSONObject data = jsonObject.getJSONObject("data");
//	            JSONArray Customers = data.getJSONArray("customers");
//	            
//	            if (Customers.length() > 0) {
//	                JSONObject firstCustomers = Customers.getJSONObject(0);
//	                customerNumber = firstCustomers.getString("customerNumber");
//	            }
//		}
			
			if(IsinvoiceNumber) {
	     		String responseBody = result.getBody().asString();
	     		
	     		JSONObject jsonObject = new JSONObject(responseBody);
	     		JSONArray Invoices = jsonObject.getJSONObject("data").getJSONArray("invoices");
	     		 if (Invoices.length() > 0) {
		                JSONObject firstCustomers = Invoices.getJSONObject(0);
		                InvoiceNumber = firstCustomers.getString("invoiceNumber");
		            }
		}
			return result;
	
}
	
	public Response applicationSalesDeletePayload(String token,String name ) {
		File jsonDataInFile;
//		requestSpecification =getRequestWithJSONHeaders();
		requestSpecification = getRequestWithJSONHeadersToken(token);
		switch (name){
		case "WAR-77 delete customer":
			application_ENDPOINT_PATH="customers/delete";
			String requestBody1 = "{ \"ids\": [ \"" + customerNumber + "\" ] }";
			requestSpecification.body(requestBody1);
//			 jsonDataInFile = new File(System.getProperty("user.dir")+"/src/test/resources/Payloads/CustomerPayloads/WAR 77 delete customer.json");
//			requestSpecification.body(jsonDataInFile);
			break;
		case "WAR-163 delete invoice":
			application_ENDPOINT_PATH="sales/invoices";
			String requestBody2 = "{ \"invoiceNumbers\": [ \"" + InvoiceNumber + "\" ] }";
			requestSpecification.body(requestBody2);
			break;
		case "WAR-293 delete customer special price":
			application_ENDPOINT_PATH="customers/specialPrices";
//			String requestBody = "{ \"priceIds\": [ \"[\"12248\"]\" ] }";
			String requestBody = "{ \"priceIds\": [\"12248\"] }";
			requestSpecification.body(requestBody);
//			 jsonDataInFile = new File(System.getProperty("user.dir")+"/src/test/resources/Payloads/CustomerPayloads/WAR 293 delete customer special price.json");
//		requestSpecification.body(jsonDataInFile);
			break;
		
		
		}
			result=requestSpecification.delete(getBaseUrl() + application_ENDPOINT_PATH);
	     	return result;
	
}
}