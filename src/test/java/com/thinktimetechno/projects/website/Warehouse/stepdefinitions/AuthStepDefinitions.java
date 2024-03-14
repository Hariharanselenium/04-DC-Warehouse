package com.thinktimetechno.projects.website.Warehouse.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;

import com.thinktimetechno.Warehouse.endpoints.AuthEndPoints;


public class AuthStepDefinitions {

	private AuthEndPoints auth;

	String responseBody;
	

	public AuthStepDefinitions(AuthEndPoints auth) {
		this.auth = auth;
	}


	@Given("I send a Post request for Auth from {string} and capture the response body")
	public void iPlaceAnOrderForApplicationSaleForTheJsonFile(String jsonFile) throws IOException {
		auth.applicationSalesCreatePayload(jsonFile);
		System.out.println(auth.result.getBody().asString());
	}

	@Given("I send a GET request for Auth from {string} and capture the response body")
	public void i_send_a_get_request_with_the_request_body_from_and_capture_the_response_body(String name)
			throws IOException {

		auth.applicationSalesfetchPayload(name);
		 System.out.println(auth.result.getBody().asString());
//		System.out.println(design.applicationSalesCreatePayload(response.getBody().asString()));
	}

	@When("The order request response for Auth has a {string} response code")
	public void the_order_request_response_has_a_response_code(String value) {
		int intValue = Integer.parseInt(value);
		auth.verifyResponseStatusValue(auth.result, intValue);
	}
	
	@Then("the response body for Auth should contain key {string} with value {string}")
	public void verifyResposeResult(String key,String value) {
		auth.verifyResponseKeyValues(key, value, auth.result);
	}
}
