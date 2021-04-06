package com.oktaliem.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
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
