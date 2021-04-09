package com.oktaliem;

import com.oktaliem.page.*;
import com.oktaliem.page.SeleniumPage;
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
    public BukalapakPage bukalapakPage(){return new BukalapakPage(driver);}
    public SeleniumPage seleniumPage(){return new SeleniumPage(driver);}
    public YahooPage yahooPage(){return new YahooPage(driver);}
}
