package com.oktaliem;

import com.oktaliem.page.webactions.Log;
import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.visual_regression_tracker.sdk_java.VisualRegressionTracker;
import io.visual_regression_tracker.sdk_java.VisualRegressionTrackerConfig;

import java.io.IOException;

/**
 * Created by oktaliem
 */
@CucumberOptions(features = {"src/test/java/com/oktaliem/features"})
public class CucumberJvmTest extends AbstractTestNGCucumberTests {

    public static VisualRegressionTracker eye;
    private static final VisualRegressionTrackerConfig config = VisualRegressionTrackerConfig.builder()
            .apiUrl("http://localhost:4200")
            .apiKey("D3GA73G7F4M7DAGK0HJVRVV0P418")
            .project("Default project")
            .branchName("master")
            .enableSoftAssert(true)
            .ciBuildId("Random Test")
            .build();

    @Before
    public void before(Scenario scenario) throws IOException {
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println(" _____ _____ ____ _____   ____ _____  _    ____ _____ _____ ____  ");
        System.out.println("|_   _| ____/ ___|_   _| / ___|_   _|/ \\  |  _ \\_   _| ____|  _ \\ ");
        System.out.println("  | | |  _| \\___ \\ | |   \\___ \\ | | / _ \\ | |_) || | |  _| | | | |");
        System.out.println("  | | | |___ ___) || |    ___) || |/ ___ \\|  _ < | | | |___| |_| |");
        System.out.println("  |_| |_____|____/ |_|   |____/ |_/_/   \\_\\_| \\_\\|_| |_____|____/ ");
        System.out.println("-------------------------------------------------------------------------------------");
        Log.info("I am on Scenario: " + scenario.getName());
        Log.info("With Tag: " + scenario.getSourceTagNames());
        eye = new VisualRegressionTracker(config);
        eye.start();
    }

    @After
    public void after(Scenario scenario) throws IOException {
        Log.info("Test Status " + scenario.getStatus());
        eye.stop();
    }

}