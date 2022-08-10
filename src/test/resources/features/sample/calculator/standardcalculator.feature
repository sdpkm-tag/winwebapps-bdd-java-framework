@Calculator @winapps
Feature: This is to test calculation feature of the Windows Calculator App
Sample user story details (As a ...., I want to ...., So that .... ), JIRA ticket references etc can be provided here

  @multiple @regression
  Scenario Outline: Basic test of "<operation>" operation using data table
    Given I have keyed "<first_operand>" into the calculator
    And I have clicked on "<operation>" button
    And I have keyed "<second_operand>" into the calculator
    When I press on "=" button to get the calculation result
    Then "<output>" should be displayed on the result screen
    Examples:
      | first_operand | second_operand | operation | output  |
      | 20            | 30             | +         | 50      |
      | 2             | 5              | x         | 7       |
      | 100           | 40             | ÷         | 2.5     |
      | 675.86        | 46.897         | -         | 628.963 |

  @divide
  Scenario: Division of two numbers
    Given I am on standard calculator screen
    When I clicked "3" into the calculator
    And I clicked on "÷" button
    And I clicked "2" into the calculator
    And I clicked on "=" button
    Then "1.5" should be displayed on the result screen

  @add @smoke
  Scenario: Addition of two numbers
    Given I am on standard calculator screen
    When I clicked "3" into the calculator
    And I clicked on "+" button
    And I clicked "9" into the calculator
    And I clicked on "=" button
    Then "12" should be displayed on the result screen

  @multiply
  Scenario: Multiplication of two numbers
    Given I am on standard calculator screen
    When I keyed "56523" into the calculator
    And I clicked on "x" button
    And I keyed "876.59" into the calculator
    And I clicked on "=" button
    Then "49,547,496.57" should be displayed on the result screen

  @subtract
  Scenario: Subtraction of two numbers
    Given I am on standard calculator screen
    When I keyed "5897547878623" into the calculator
    And I clicked on "-" button
    And I keyed "2758.578" into the calculator
    And I clicked on "=" button
    Then "5,897,547,875,864.422" should be displayed on the result screen