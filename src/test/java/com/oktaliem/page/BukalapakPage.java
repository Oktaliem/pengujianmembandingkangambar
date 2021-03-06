package com.oktaliem.page;

import io.qameta.allure.Step;
import io.visual_regression_tracker.sdk_java.IgnoreAreas;
import io.visual_regression_tracker.sdk_java.TestRunOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Collections;

import static com.oktaliem.CucumberJvmTest.eye;

public class BukalapakPage extends BasePage {

    public BukalapakPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public void performBukalapakVisualTrackerTest() throws IOException {
        // get visible part screenshot and run visual regression testing
        eye.track("Bukalapak Home page",
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64),
                TestRunOptions.builder()
                        .device("Xiaomi mibook pro")
                        .os("Ubuntu 20.04")
                        .browser("Chrome")
                        .viewport("1240x1024")
                        .diffTollerancePercent(0.0f)
                        .ignoreAreas(Collections.singletonList(
                                IgnoreAreas.builder()
                                        .x(10L)
                                        .y(10L)
                                        .width(100L)
                                        .height(200L)
                                        .build()
                        )).build());


        // get screenshot and set as allure report attachment
        saveScreenshotPNG(driver);

        // get entire page screenshot with Shutterbug and run visual regression testing
        eye.track("Bukalapak Home Page - Shutterbug Test",
                fullScreenShootShutterbug("Bukalapak Home Page - Shutterbug Test"));

        // get entire page screenshot with Ashot and run visual regression testing
        eye.track("Bukalapak Home Page - Ashot Test",
                fullScreenShotAshot("Bukalapak Home Page - Ashot Test"));
    }

}
