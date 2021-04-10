# Created by oktaliem
@yahoo
Feature: Sample visual test for Yahoo Finance

  Scenario: Bank BCA Stock Page
    When I go to https://sg.finance.yahoo.com/quote/BBCA.JK?p=BBCA.JK&.tsrc=fin-srch
    Then I am landing to stock historical data and summary "BBCA"
    
  Scenario: Bank Mandiri Stock Page
    When I go to https://sg.finance.yahoo.com/quote/BMRI.JK?p=BMRI.JK&.tsrc=fin-srch
    Then I am landing to stock historical data and summary "BMRI"

  Scenario: Bank BRI Stock Page
    When I go to https://sg.finance.yahoo.com/quote/BBRI.JK?p=BBRI.JK&.tsrc=fin-srch
    Then I am landing to stock historical data and summary "BBRI"