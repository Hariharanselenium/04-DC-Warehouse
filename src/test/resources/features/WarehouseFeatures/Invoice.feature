@WarehouseTest
@InvoiceTest
Feature: Invoice Tests
This feature includes Invoice tests using RESTFul services



 Scenario: TC-01 war-210 download invoice pdf
Given I send a Get request for Invoice from "war-210 download invoice pdf" and capture the response body
When The order request response for Invoice from has a '200' response code
