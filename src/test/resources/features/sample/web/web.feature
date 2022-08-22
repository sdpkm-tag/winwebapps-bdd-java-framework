@WebApp
Feature: Login page feature
  Scenario: Login page title
    Given user is on login page
    When user gets the title of the page
    Then page title should be "Login - My Store1"

  Scenario: Forgot Password link
    Given user is on login page
    Then forgot your password link should be displayed