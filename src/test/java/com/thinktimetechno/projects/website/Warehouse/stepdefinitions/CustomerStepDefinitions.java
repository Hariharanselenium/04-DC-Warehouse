package com.thinktimetechno.projects.website.Warehouse.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;

import com.thinktimetechno.Warehouse.endpoints.CustomerEndPoints;

public class CustomerStepDefinitions {

	private CustomerEndPoints custom;

	String responseBody;
	io.restassured.response.Response response;

	public CustomerStepDefinitions(CustomerEndPoints custom) {
		this.custom = custom;
	}

//	AuthExample auth =new AuthExample();
	@Given("I send a POST request for Customer with the request body from {string} and capture the response body")
	public void PostRequest(String jsonFile) throws IOException {
		custom.applicationSalesCreatePayload(custom.token,jsonFile);
		System.out.println(custom.result.getBody().asString());
	}

	@Given("I send a PUT request for Customer with the request body from {string} and capture the response body")
	public void PutRequest(String jsonFile)
			throws IOException {

		custom.applicationSalesUpdatePayload(custom.token,jsonFile);
		System.out.println(custom.result.getBody().asString());

	}

	@Given("I send a GET request for Customer from {string} and capture the response body")
	public void GetRequest(String name)
			throws IOException {

		 custom.applicationSalesfetchPayload(custom.token,name);
		 System.out.println(custom.result.getBody().asString());
	}

	@Given("I send a DELETE request for Customer from {string} and capture the response body")
	public void DeleteRequest(String name) {
		custom.applicationSalesDeletePayload(custom.token,name);
	}

	@When("The order request response for Customer has a {string} response code")
	public void VerifyResponseCode(String value) {
		int intValue = Integer.parseInt(value);
		custom.verifyResponseStatusValue(custom.result, intValue);
	}
	
	@Then("the response body for Customer should contain key {string} with value {string}")
	public void verifyResposeResult(String key,String value) {
		custom.verifyResponseKeyValues(key, value, custom.result);
	}
}
