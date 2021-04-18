package com.oktaliem.stepdefs;

import com.oktaliem.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.oktaliem.CucumberJvmTest.testCase;

public class AbstractStepDef {
    protected static WebDriver driver;
    public User user;

    public WebDriver getDriver() {
        try {
            if (System.getProperty("browser").equals("zalenium")) {
                DesiredCapabilities capability = DesiredCapabilities.chrome();
                capability.setBrowserName("chrome");
                capability.setCapability("name", testCase);
                try {
                    driver = new RemoteWebDriver(new URL(System.getProperty("grid_url")), capability);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        user = new User(driver);
        return driver;
    }
}
