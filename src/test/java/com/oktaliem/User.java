package com.oktaliem;

import com.oktaliem.page.*;
import org.openqa.selenium.WebDriver;

/**
 * Created by oktaliem
 */
public class User {

    WebDriver driver;

    public User(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage loginPage() {return new LoginPage(driver);}
    public BlibliPage blibliPage(){return new BlibliPage((driver));}
}
