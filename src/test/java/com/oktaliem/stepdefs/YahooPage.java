package com.oktaliem.stepdefs;

import com.oktaliem.User;
import cucumber.api.java.en.Then;


public class YahooPage extends AbstractStepDef{

    @Then("^I am landing to stock historical data and summary \"([^\"]*)\"$")
    public void iAmLandingToStockHistoricalDataAndSummary(String bankName) throws Throwable {
        user = new User(driver);
        user.yahooPage().performYahooVisualTest(bankName);
    }
}
