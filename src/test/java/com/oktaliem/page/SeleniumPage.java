package com.oktaliem.page;

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

    @FindBy(css = ".col > .row > div:nth-of-type(1) .card-body")
    protected WebElement seleniumWebDriver;

    @FindBy(css = ".col div:nth-of-type(2) .card-body")
    protected WebElement seleniumIde;

    @FindBy(css = ".col div:nth-of-type(3) .card-body")
    protected WebElement seleniumGrid;

    @Step
    public void performSeleniumVisualTest(String chartName) throws IOException, InterruptedException {
        switch (chartName) {
            case SELENIUM_WEBDRIVER:
                waitUntilLocatorIsVisible(seleniumWebDriver, 30);
                getSeleniumElementScreenShot(seleniumWebDriver, chartName);
                break;
            case SELENIUM_IDE:
            case SELENIUM:
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

        if (chartName.equals(SELENIUM)) {
            performVisualTestNotIdentical(SELENIUM_IDE, SELENIUM_GRID + EXPECTED, SELENIUM + DIFF);
        } else {
            String expected_image = chartName + EXPECTED;
            String diff_image = chartName + DIFF;
            performVisualTest(chartName, expected_image, diff_image);
        }
    }

}
