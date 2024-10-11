#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag

Feature: Navigate to Careers Page

  Scenario: Access MoneyLion careers page
    Given I am a new customer
    When I click on the Careers link
    Then I should be redirected to the MoneyLion's careers page
    And I should see the "WHERE WE WORK" heading
    And I should see the locations "New York City, with offices in Sioux Falls, and Kuala Lumpur, Malaysia, and we also support remote team members around the globe."


