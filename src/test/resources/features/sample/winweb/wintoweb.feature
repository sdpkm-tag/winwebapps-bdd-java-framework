@WinApp @WebApp @Calculator
Feature: This is to test calculation feature of the Windows Calculator App and verifying the result in a web app
  Sample user story details (As a ...., I want to ...., So that .... ), JIRA ticket references etc can be provided here

  @addwinweb @madetopass
  Scenario: Addition of two numbers - To test pass on web
    Given I am on standard calculator screen
    When I clicked "4" into the calculator
    And I clicked on "+" button
    And I clicked "3" into the calculator
    And I clicked on "=" button
    Then "7" should be displayed on the result screen
    And I note down result and close Windows Calculator App and Open WebApp
    When I did a sum "+" operation of "4" and "3" and clicked on "=" button
    Then I get "7" as result and this is same as result shown by Windows calculator App

  @addwinweb @madetofail
  Scenario: Addition of two numbers - To test failure on web
    Given I am on standard calculator screen
    When I clicked "4" into the calculator
    And I clicked on "+" button
    And I clicked "3" into the calculator
    And I clicked on "=" button
    Then "7" should be displayed on the result screen
    And I note down result and close Windows Calculator App and Open WebApp
    When I did a sum "+" operation of "4" and "3" and clicked on "=" button
    Then I get "9" as result and this is same as result shown by Windows calculator App

