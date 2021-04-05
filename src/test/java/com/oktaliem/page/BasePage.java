package com.oktaliem.page;

import com.oktaliem.page.webactions.BaseActions;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by oktaliem
 */
public class BasePage extends BaseActions {
    protected static WebDriver driver;

    public BasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        BasePage.driver = driver;
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
