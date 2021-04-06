# Created by oktaliem
@Selenium
Feature: Sample visual test for selenium.dev

  @Selenium01
  Scenario: Home page visual test Selenium WebDriver
    When I go to https://www.selenium.dev/
    Then I am landing to Selenium Home Page and check element "Selenium WebDriver"


  @Selenium02
  Scenario: Home page visual test Selenium IDE
    When I go to https://www.selenium.dev/
    Then I am landing to Selenium Home Page and check element "Selenium IDE"


  @Selenium03
  Scenario: Home page visual test Selenium Grid
    When I go to https://www.selenium.dev/
    Then I am landing to Selenium Home Page and check element "Selenium Grid"

  @Selenium04
  Scenario: Home page visual test should not identical
    When I go to https://www.selenium.dev/
    Then I am landing to Selenium Home Page and check element "Selenium"