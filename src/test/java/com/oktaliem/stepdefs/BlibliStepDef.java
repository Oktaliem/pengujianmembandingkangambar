package com.oktaliem.stepdefs;

import com.oktaliem.User;
import cucumber.api.java.en.Then;

import java.io.IOException;

public class BlibliStepDef extends AbstractStepDef {

    @Then("^I am landing to Blibli Home Page$")
    public void iAmLandingToBlibliHomePage() throws IOException {
        user = new User(driver);
        user.blibliPage().performVisualTest();
    }
}
