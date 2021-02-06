package com.demo.bdd.steps;

import com.demo.bdd.api.RestUtil;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class FixtureSteps {

    static RestUtil util= new RestUtil();
    
 

@Given("^Retrieve all fixtures$")
public void retrieve_all_fixtures() {
    // Write code here that turns the phrase above into concrete actions
  util.getAllFixtures();
}

 

@Then("^Assert that each of the (\\d+) fixtures has a fixtureId value$")
public void assert_that_each_of_the_fixtures_has_a_fixtureId_value(int arg1) {
    // Write code here that turns the phrase above into concrete actions
    util.VerifyFixturevalue();
}

@Then("^Create a new Fixture with ID(\\d+)$")
public void create_a_new_Fixture_with_ID(int arg1) {
    // Write code here that turns the phrase above into concrete actions
    util.CreateNewFixture(arg1);
}

@Then("^Validate the Fixture Count (\\d+)$")
public void validate_the_Fixture_Count(int arg1) {
    // Write code here that turns the phrase above into concrete actions
    util.verifyFixtureCount( arg1);
}

@Then("^Delete Existing Fixture ID (\\d+)$")
public void delete_Existing_Fixture_ID(int arg1) {
    // Write code here that turns the phrase above into concrete actions
    util.deleteFixture(arg1);
}

@Then("^Close the Report$")
public void CloseReport() {
    // Write code here that turns the phrase above into concrete actions
    util.tearDown();
    }
 
     
}
