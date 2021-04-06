package com.oktaliem.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

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