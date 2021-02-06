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