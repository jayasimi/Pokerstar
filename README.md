
cucumber-rest-assured-extentreports vanilla project This project is a plan project with Cucumber framework to automate Rest API's.

Automation of restfull api or webservices projects with Rest Assured This also includes the extent reporting.

Table of Contents (Optional) Preconditions MAVEN_Dependencies SetUp Add_features_and_respective_step_definitions Support Preconditions

Maven
Java 8
Cucumber Eclipse plugin MAVEN_Dependencies Add the following dependencies to your pom.xml All below dependencies are compatible.
io.cucumber cucumber-java 2.4.0 io.cucumber cucumber-junit 2.4.0 test com.aventstack extentreports 3.0.0 org.testng testng 6.11 test io.rest-assured rest-assured 3.0.5 test com.jayway.jsonpath json-path 2.4.0 com.fasterxml.jackson.core jackson-databind 2.6.3 net.javacrumbs.json-unit json-unit 1.23.0 io.rest-assured rest-assured 3.0.2 com.google.guava guava 18.0 org.jsoup jsoup 1.8.3 org.xerial sqlite-jdbc 3.8.11.1 org.freemarker freemarker 2.3.23 org.mongodb mongodb-driver 3.0.4 com.googlecode.json-simple json-simple 1.1.1 jakarta.xml.bind jakarta.xml.bind-api 2.3.2 org.glassfish.jaxb jaxb-runtime 2.3.2
SetUp Configure extent-config.xml

standard
	<!-- document encoding -->  <!-- defaults to UTF-8 -->
	<encoding>UTF-8</encoding>

	<!-- protocol for script and stylesheets -->   <!-- defaults to https -->
	<protocol>https</protocol>

	<!-- title of the document -->
	<documentTitle>Cucumber Framework</documentTitle>

	<!-- report name - displayed at top-nav -->
	<reportName>Cucumber Extent Report</reportName>

	<!-- global date format override -->  <!-- defaults to yyyy-MM-dd -->
	<dateFormat>yyyy-MM-dd</dateFormat>

	<!-- global time format override -->   <!-- defaults to HH:mm:ss -->
	<timeFormat>HH:mm:ss</timeFormat>

	<!-- custom javascript -->
	<scripts>
  <![CDATA[
    $(document).ready(function() {
    
    });
  ]]>
	</scripts>

	<!-- custom styles -->
	<styles>
  <![CDATA[
    
  ]]>
	</styles>
</configuration>
create a runner class for cucumber project Run with tags. Here i have added @get,@post,@put,@getfail,@postfail,@putfail tags.
package com.demo.bdd.runner;

import org.junit.AfterClass; import org.junit.BeforeClass; import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions; import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class) @CucumberOptions( features = "classpath:cucumber/Fixture.feature", glue = "com.demo.bdd.steps", monochrome = true, format = "json:target/cucumber-json-report.json"

) public class RunFixtureTest {

}

Add_features_and_respective_step_definitions @tagToIdentifyThatBeginAfterShouldRunForThisFeatureOnly Feature: Fixture As i want to create and Delete Fixture

Scenario Outline: Fixture CRUD

Given Retrieve all fixtures
Then Validate the Fixture Count <initialCount> 
Then Assert that each of the 3 fixtures has a fixtureId value
And Create a new Fixture with ID<ID>
Then Validate the Fixture Count <incrementedCount> 
And Delete Existing Fixture ID <ID>
Then Validate the Fixture Count <decrementedCount>
Then Close the Report

Examples: 
| initialCount | ID |incrementedCount | decrementedCount |
| 3 | 4 | 4 |3|
