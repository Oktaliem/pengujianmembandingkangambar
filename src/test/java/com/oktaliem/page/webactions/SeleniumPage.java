package com.oktaliem.page.webactions;

import com.oktaliem.page.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

import static com.oktaliem.utils.constants.*;

public class SeleniumPage extends BasePage {

    public SeleniumPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//body/section[2]/div[1]/div[1]")
    protected WebElement seleniumWebDriver;

    @FindBy(xpath = "//body/section[2]/div[1]/div[2]")
    protected WebElement seleniumIde;

    @FindBy(xpath = "//body/section[2]/div[1]/div[3]")
    protected WebElement seleniumGrid;

    @Step
    public void performSeleniumVisualTest(String chartName) throws IOException, InterruptedException {
        switch (chartName) {
            case SELENIUM_WEBDRIVER:
                waitUntilLocatorIsVisible(seleniumWebDriver, 30);
                getSeleniumElementScreenShot(seleniumWebDriver, chartName);
                break;
            case SELENIUM_IDE:
                waitUntilLocatorIsVisible(seleniumIde, 30);
                getShutterbugElementScreenshot(seleniumIde, chartName);
                break;
            case SELENIUM_GRID:
                waitUntilLocatorIsVisible(seleniumGrid, 30);
                getAShotElementScreensAshot(seleniumGrid, chartName);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + chartName);
        }
        String expected_image = chartName + EXPECTED;
        String diff_image = chartName + DIFF;
        performVisualTest(chartName, expected_image, diff_image);
    }
}
