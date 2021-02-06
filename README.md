# Pokerstar

cucumber-rest-assured-extentreports vanilla project
This project is a plan project with Cucumber framework to automate Rest API's.

Automation of restfull api or webservices projects with Rest Assured
This also includes the extent reporting.
 
Table of Contents (Optional)
Preconditions
MAVEN_Dependencies
SetUp
Add_features_and_respective_step_definitions
Support
Preconditions
- Maven 
- Java 8
- Cucumber Eclipse plugin
MAVEN_Dependencies
Add the following dependencies to your pom.xml All below dependencies are compatible.
 <dependencies>
		<dependency>
			<groupId>io.cucumber</groupId>
			<artifactId>cucumber-java</artifactId>
			<version>2.4.0</version>
		</dependency>
		<dependency>
			<groupId>io.cucumber</groupId>
			<artifactId>cucumber-junit</artifactId>
			<version>2.4.0</version>
			<scope>test</scope>
		</dependency>
		<dependency>
    <groupId>com.aventstack</groupId>
    <artifactId>extentreports</artifactId>
    <version>3.0.0</version>
</dependency>
		 <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>6.11</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>3.0.5</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.jayway.jsonpath</groupId>
            <artifactId>json-path</artifactId>
            <version>2.4.0</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.6.3</version>
        </dependency>
        <dependency>
            <groupId>net.javacrumbs.json-unit</groupId>
            <artifactId>json-unit</artifactId>
            <version>1.23.0</version>
        </dependency>
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>3.0.2</version>
        </dependency>
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>18.0</version>
        </dependency>
        <dependency>
    <groupId>org.jsoup</groupId>
    <artifactId>jsoup</artifactId>
    <version>1.8.3</version>
</dependency>
<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.8.11.1</version>
</dependency>
<dependency>
    <groupId>org.freemarker</groupId>
    <artifactId>freemarker</artifactId>
    <version>2.3.23</version>
</dependency>
<dependency>
    <groupId>org.mongodb</groupId>
    <artifactId>mongodb-driver</artifactId>
    <version>3.0.4</version>
</dependency>
        <dependency>
    <groupId>com.googlecode.json-simple</groupId>
    <artifactId>json-simple</artifactId>
    <version>1.1.1</version>
</dependency>
        <dependency>
    <groupId>jakarta.xml.bind</groupId>
    <artifactId>jakarta.xml.bind-api</artifactId>
    <version>2.3.2</version>
</dependency>

<!-- Runtime, com.sun.xml.bind module -->
<dependency>
    <groupId>org.glassfish.jaxb</groupId>
    <artifactId>jaxb-runtime</artifactId>
    <version>2.3.2</version>
</dependency>
	</dependencies>
	 
 
SetUp
Configure extent-config.xml
<?xml version="1.0" encoding="UTF-8"?>
<extentreports>
	<configuration>
		<!-- report theme --> <!-- standard, dark -->
		<theme>standard</theme>

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
</extentreports>
create a runner class for cucumber project
Run with tags. Here i have added @get,@post,@put,@getfail,@postfail,@putfail tags.
 
 package com.demo.bdd.runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

 

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		  features = "classpath:cucumber/Fixture.feature",
		  glue = "com.demo.bdd.steps",
		monochrome = true,
format = "json:target/cucumber-json-report.json"

)
public class RunFixtureTest {
	
}
 
 
Add_features_and_respective_step_definitions
 @tagToIdentifyThatBeginAfterShouldRunForThisFeatureOnly
Feature: Fixture
 As i want to create and Delete Fixture

 
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
  
