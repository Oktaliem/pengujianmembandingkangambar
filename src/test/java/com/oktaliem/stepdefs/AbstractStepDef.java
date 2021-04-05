package com.oktaliem.stepdefs;

import com.oktaliem.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AbstractStepDef {
    protected static WebDriver driver;
    public User user;

    public WebDriver getDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        user = new User(driver);
        return driver;
    }


}
