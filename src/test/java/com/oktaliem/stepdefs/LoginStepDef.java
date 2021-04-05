package com.oktaliem.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class LoginStepDef extends AbstractStepDef {
    public WebDriver driver = getDriver();

    @After
    public void teardown() {
        driver.quit();
    }

    @Given("^I go to (.*)$")
    public void userGoesToLoginPage(String url) {
        user.loginPage().openURL(url);
    }
}
