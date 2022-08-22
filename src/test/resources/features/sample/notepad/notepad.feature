@WinApp @Notepad
Feature: This is to test Notepad features
  Sample user story details (As a ...., I want to ...., So that .... ), JIRA ticket references etc can be provided her

  @Notepad
  Scenario: Launch notepad, verify "About Notepad" option, enter some text in notepad and save the file.
    Given I have launched Notepad application
    When I click on "Help" menu option
    And I select "About Notepad" option
    Then I view "Microsoft Windows" text appearing on screen
    When I click on "OK" button
    Then Mouse cursor goes to the "Text Editor" window on notepad application
#    When I enter text "This is a sample text\n\nThis is a text in newline\n\tfinaltext"
#    And I click on "File" menu option from menu bar
#    And I select "Save As..." option
#    And I enter file name as "test.txt"
#    And I click on "Save" button
#    Then Mouse cursoer goes back to the "Text Editor" window on notepad application
#    And Title bar value is shown as "Test.txt - Notepad"