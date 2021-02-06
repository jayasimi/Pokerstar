package com.demo.bdd.api;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testng.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.demo.bdd.util.FileUtil;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestUtil {

	//config.propertiea
	static String uri = FileUtil.getProperty("url");
	static ExtentTest logger;
    static ExtentReports extent;
    static ExtentHtmlReporter reporter;
	
 

    
    public static void tearDown(){
    	extent.flush();
    	extent.close();
    	 System.out.print("end");
    }

	public void CreateNewFixture(int id) {
		// TODO Auto-generated method stub
		try {
		RestAssured.baseURI = uri + "/fixture";
		String payload=FileUtil.replaceText(FileUtil.getFileContent("CreateFixture.json"),id+"");
		RestAssured.useRelaxedHTTPSValidation();
		 
		Response r = RestAssured.given().header("Accept", "application/json").contentType("application/json")
				.body(payload)
				.when().post().then().
				// assertThat().statusCode(200).and().contentType(ContentType.JSON).
				extract().response();
		System.out.print(r.statusCode() + "");
		if (r.statusCode() == 200) {
			logger.log(Status.PASS, "Successfully Created new Fixture");
			 
		} else {
			logger.log(Status.FAIL, "Failed to create new Fixture");
			logger.log(Status.FAIL, "Failed to create new Fixture");
		}
		}
		catch(Exception e) {
			System.out.print(e.getLocalizedMessage());
			logger.log(Status.FAIL, "Failed to create new Fixture, Following Exception occured, ");
		}
	}

	public void verifyFixtureCount(int expectedCount) {
		try{
	 
		RestAssured.baseURI = uri;
		RestAssured.useRelaxedHTTPSValidation();
		Response r = RestAssured.given().get("/fixtures").then().extract().response();
		System.out.print(r.statusCode() + "");
		if (r.statusCode() == 200) {
			String ResponseString = r.asString();
			System.out.println("JSON Response:\t" + ResponseString);
			JsonPath js = new JsonPath(ResponseString);
			Response response = r.then().extract().response();
			int op = response.jsonPath().getList("$").size();
			if (op == expectedCount) {
				logger.log(Status.PASS, "Successfully Verified Fixture Count");
			} else {
				logger.log(Status.FAIL, "Failed to Verify Fixture Count");
			}
 
		} else {
			logger.log(Status.FAIL, "Failed to Verify Fixture Count");
		}
		}catch(Exception e) {
			logger.log(Status.FAIL, "Failed to Verify Fixture Count, Following Exception occured,");
		}

	}
	 
	public void getAllFixtures() {
		try {
			String location=System.getProperty("user.dir");
			String path=FileUtil.createReportFile();
			  reporter=new ExtentHtmlReporter(path);
			    extent = new ExtentReports();
			    extent.attachReporter(reporter);
			      logger=extent.createTest("Verify Fixture CRUD API");
			 RestAssured.baseURI = uri;
			 Response  r = RestAssured.given().get("/fixtures").then().extract().response();
			 System.out.print(r.statusCode()+"");
			 logger= extent.createTest("Pass Test", "Login");
		  
		}
		catch(Exception e) {
			System.out.print(e.getLocalizedMessage());
			 
			logger.log(Status.FAIL, "Failed to Get Fixtures, Following Exception occured, "+e.getLocalizedMessage());
		}
		 
		}

	public void VerifyFixturevalue() {
		RestAssured.baseURI = uri;
		RestAssured.useRelaxedHTTPSValidation();
		Response r = RestAssured.given().get("/fixtures").then().extract().response();
		System.out.print(r.statusCode() + "");
		if (r.statusCode() == 200) {
			String ResponseString = r.asString();
			System.out.println("JSON Response:\t" + ResponseString);
			JsonPath js = new JsonPath(ResponseString);
			Response response = r.then().extract().response();
			int op = response.jsonPath().getList("$").size();
			int j = 0;
			for (int i = 0; i < op; i++) {
				j = j + 1;
				String test = response.jsonPath().get("fixtureId[" + i + "]");
				System.out.println(test);
				int test2 = Integer.parseInt(test);
				Assert.assertEquals(j, test2);
			}
			logger.log(Status.PASS, "Successfully Verified the Fixture values");
		} else {
			logger.log(Status.FAIL, "Failed to verify the Fixture values");
		}

	}

	public void deleteFixture(int id) {
		try {
		// TODO Auto-generated method stub
		RestAssured.baseURI = uri + "/fixture";
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		Response response = request.delete("/" + id);
		int statusCode = response.getStatusCode();
		System.out.println(response.asString());
		Assert.assertEquals(statusCode, 200);
		 
		logger.log(Status.PASS, "Successfully Deleted the Fixture");
		}
		catch(Exception e) {
			logger.log(Status.FAIL, "Failed to delete the Fixtures");
		}

	}
}
