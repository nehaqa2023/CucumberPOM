Feature: Login into firebase application

  Scenario: Login with valid user and valid password
    Given user open salesforce application
    When user on "LoginPage"
    When user enters value into text box username as "userId"
    When user enters value into text box password as "password"
    When click on Login button
    When user on "Homepage"
    When verify we can see "HomePage"
    Then user logout

  Scenario: Login Error message valid user and clear password
    Given user open salesforce application
    When user on "LoginPage"
    When user enters value into text box username as "userId"
    When click on Login button
    Then verify error Please enter a password

  Scenario: Successful login by checking remember me
    Given user open salesforce application
    When user on "LoginPage"
    When user enters value into text box username as "userId"
    When user enters value into text box password as "password"
    When click remenberMe checkbox
    When click on Login button
    When user on "Homepage"
    When verify we can see "HomePage"
    Then user logout
    