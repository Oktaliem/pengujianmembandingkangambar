package com.oktaliem.stepdefs;

import com.oktaliem.User;
import cucumber.api.java.en.Then;

public class SeleniumSteDef extends AbstractStepDef {

    @Then("^I am landing to Selenium Home Page and check element \"([^\"]*)\"$")
    public void iAmLandingToSeleniumHomePageAndCheckElement(String element) throws Throwable {
        user = new User(driver);
        user.seleniumPage().performSeleniumVisualTest(element);
    }
}
