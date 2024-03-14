package com.thinktimetechno.hooks;

import com.thinktimetechno.Warehouse.endpoints.AuthEndPoints;
import com.thinktimetechno.driver.DriverManager;
import com.thinktimetechno.helpers.PropertiesHelpers;
import com.thinktimetechno.keywords.WebUI;
import com.thinktimetechno.report.AllureManager;
import com.thinktimetechno.report.ExtentReportManager;
import com.thinktimetechno.report.ExtentTestManager;
import io.cucumber.java.*;
import io.cucumber.java.en.Given;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

	 AuthEndPoints auth;

	String responseBody;
	



    TestContext testContext;

//    public Hooks(TestContext context) {
//        testContext = context;
//    }
    public Hooks(TestContext context, AuthEndPoints authEndPoints) {
    	testContext = context;
        this.auth = authEndPoints;
    }

    @BeforeAll
    public static void before_all() {
        System.out.println("================ BEFORE ALL ================");
        PropertiesHelpers.loadAllFiles(); //Load Config and Locators
        AllureManager.setAllureEnvironmentInformation(); //Setup Allure Report

    }

    @AfterAll
    public static void after_all() {
        System.out.println("================ AFTER ALL ================");
        
    }

//    @Before
//    public void beforeScenario() {
//        //System.out.println("Starting Driver in Hooks: " + DriverManager.getDriver());
//    
//    }

    @Before
    public void beforeScenario(Scenario scenario) throws IOException {
        // Your setup code here
        String jsonFile = "Login.json"; // Define your JSON file path here or retrieve it from a source
        auth.applicationSalesCreatePayload(jsonFile);
    }


    @After
    public void afterScenario(Scenario scenario) throws IOException {
        //System.out.println("Stop Driver in Hooks: " + DriverManager.getDriver());
//        WebUI.sleep(1);
//        DriverManager.quit();
//        WebUI.stopSoftAssertAll();
    	
          auth.AuthdeletePayload();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        //validate if scenario has failed then Screenshota
        if (scenario.isFailed()) {

//            final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
//            scenario.attach(screenshot, "image/png", scenario.getName());
        	scenario.attach(scenario.getName(), "", scenario.getStatus().toString());
        }

        //AllureManager.takeScreenshotStep();

    }
}


