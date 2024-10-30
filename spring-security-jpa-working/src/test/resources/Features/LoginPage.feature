Feature: feature to test login functionality

  Scenario: Check the user credentials so as to authenticate the user
    Given the user is on login page
    When the user enters the credential
    Then Authenticate the user credentials
    And Upon click on login button
    Then User is navigated towards the search page