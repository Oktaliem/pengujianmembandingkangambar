package com.oktaliem;

import com.oktaliem.page.webactions.Log;
import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.visual_regression_tracker.sdk_java.VisualRegressionTracker;
import io.visual_regression_tracker.sdk_java.VisualRegressionTrackerConfig;
import micooc.Micooc;
import micooc.model.InitializedBuild;
import org.testng.annotations.AfterClass;

import java.io.IOException;

import static com.oktaliem.utils.path.MICOO_PATH;

/**
 * Created by oktaliem
 */
@CucumberOptions(features = {"src/test/java/com/oktaliem/features"})
public class CucumberJvmTest extends AbstractTestNGCucumberTests {

    //Visual Regression Tracker set up
    public static VisualRegressionTracker eye;
    private static final VisualRegressionTrackerConfig config = VisualRegressionTrackerConfig.builder()
            .apiUrl("http://localhost:4200")
            .apiKey("HQKMJVGVP84Z4BMBR2ZCB468N3DX")
            .project("ISQA")
            .branchName("master")
            .enableSoftAssert(true)
            .ciBuildId("Demo Test")
            .build();

    //Micoo Visual Regression Test set up
    String serviceHost = "http://localhost:8123";
    String apiKey = "AK9650d2de834735bb81";
    String pid = "PID388744cac445436a8de74ee4fd24f626";
    String buildVersion = "1";
    String screenshotDirectory = MICOO_PATH;
    String serviceEngineUrl = serviceHost + "/engine";
    InitializedBuild initializedBuild;

    public static String testCase;

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
        testCase = scenario.getName();
        eye = new VisualRegressionTracker(config);
        eye.start();
    }

    @After
    public void after(Scenario scenario) throws IOException {
        Log.info("Test Status " + scenario.getStatus());
        eye.stop();
    }

    @AfterClass
    public void afterClass() {
        initializedBuild = Micooc.newBuild(serviceEngineUrl, apiKey, pid, buildVersion, screenshotDirectory);
    }

}