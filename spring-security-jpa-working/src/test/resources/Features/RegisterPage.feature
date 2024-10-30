Feature: feature to test Register functionality
  Scenario: Check the Register details to validate and Register user
    Given the user is on register page
    When the user enters the register detail
    Then Validate register details enter by user
    And click on register button
    Then User is registered and navigated to login