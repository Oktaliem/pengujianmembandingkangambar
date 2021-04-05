package com.oktaliem.page;

import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static com.oktaliem.CucumberJvmTest.eye;

public class BlibliPage extends BasePage {

    public BlibliPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public void performVisualTest() throws IOException {
        eye.track("Blibli Home page", ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64));
    }

}
