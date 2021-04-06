# Created by oktaliem
Feature: Sample visual test for selenium.dev

  @Selenium
  Scenario: Home page visual test Selenium WebDriver
    When I go to https://www.selenium.dev/
    Then I am landing to Selenium Home Page and check element "Selenium WebDriver"


  @Selenium
  Scenario: Home page visual test Selenium IDE
    When I go to https://www.selenium.dev/
    Then I am landing to Selenium Home Page and check element "Selenium IDE"


  @Selenium
  Scenario: Home page visual test Selenium Grid
    When I go to https://www.selenium.dev/
    Then I am landing to Selenium Home Page and check element "Selenium Grid"