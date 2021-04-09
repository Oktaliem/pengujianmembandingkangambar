package com.oktaliem.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class YahooPage extends BasePage{
    public YahooPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#quote-header")
    protected WebElement quoteHeader;

    @Step
    public void performYahooVisualTest(String bankName) throws IOException, InterruptedException {
        getMicooSeleniumElementScreenShot(quoteHeader,bankName);
        saveScreenshotPNG(driver);
    }
}
