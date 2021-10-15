# Created by oktaliem
@selenium
Feature: Sample visual test for selenium.dev

  @selenium01
  Scenario: Home page visual test Selenium WebDriver
    When I go to https://www.selenium.dev/
    Then I am landing to Selenium Home Page and check element "Selenium WebDriver"


  @selenium02
  Scenario: Home page visual test Selenium IDE
    When I go to https://www.selenium.dev/
    Then I am landing to Selenium Home Page and check element "Selenium IDE"


  @selenium03
  Scenario: Home page visual test Selenium Grid
    When I go to https://www.selenium.dev/
    Then I am landing to Selenium Home Page and check element "Selenium Grid"

  @selenium04
  Scenario: Home page visual test should not identical
    When I go to https://www.selenium.dev/
    Then I am landing to Selenium Home Page and check element "Selenium"