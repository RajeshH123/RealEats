@LoginFeature
Feature: Login page scenarios
#Description: The purpose of this feature is to test the Login functionality

  Background:
    Given User opens browzer And enters the URL 
    And On Home Page clicks the LogIn Link
    
  @Valid_Login
  Scenario Outline: Validate if the user is able to login application successfully with Valid credentials
    Given User enters the '<EmailID>' and '<Password>'  
    And Click on Login button
    
    Examples:    
    |EmailID    |Password|
    |XYZ@xyz.com|Password|