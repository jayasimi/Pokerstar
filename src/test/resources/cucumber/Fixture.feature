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
  