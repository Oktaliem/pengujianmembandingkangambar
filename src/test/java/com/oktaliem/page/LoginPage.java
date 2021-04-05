package com.oktaliem.page;

import com.oktaliem.page.webactions.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import static org.testng.Assert.fail;

/**
 * Created by oktaliem
 */
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public void openURL(String url) {
        goToWeb(url);
        saveScreenshotPNG(driver);
    }
}