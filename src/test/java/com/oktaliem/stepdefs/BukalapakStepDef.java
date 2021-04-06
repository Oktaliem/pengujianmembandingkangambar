package com.oktaliem.stepdefs;

import com.oktaliem.User;
import cucumber.api.java.en.Then;

import java.io.IOException;

public class BukalapakStepDef extends AbstractStepDef{

    @Then("^I am landing to Bukalapak Home Page$")
    public void iAmLandingToBukalapakHomePage() throws IOException {
        user = new User(driver);
        user.bukalapakPage().performBukalapakVisualTrackerTest();
    }
}
