package com.oktaliem.page.webactions;

import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author : Okta Liem
 */

public class BaseGeneralAction extends BaseMouseKeyboardAction {

    public BaseGeneralAction(WebDriver driver) {
        super(driver);
    }

    @Step
    protected void refreshPage() {
        driver.navigate().refresh();
        log.info("Refresh Page");
    }

    @Step
    protected void goToPreviousPage() {
        driver.navigate().back();
        log.info("Back to previous page");
    }

    @Step
    protected void goToNextPage() {
        driver.navigate().forward();
        log.info("Go to next page");
    }

    @Step
    protected void getHtmlSource(String fileName) throws IOException {
        String getActualFile = driver.getPageSource();
        File DestFile = new File(System.getProperty("user.dir") + "/src/main/resources/actualhtmltext/"
                + fileName + ".txt");
        FileUtils.writeStringToFile(DestFile, getActualFile);
        log.info("Get HTML resource succeed");
    }

    @Step
    protected void switchToSecondBrowser() {
        ArrayList<String> tabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        log.info("Go to second tab");
    }

    @Step
    protected void switchToFirstBrowser() {
        ArrayList<String> tabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        log.info("Go to first tab");
    }

    @Step
    protected String getValueWithRegex(String regex, String text) {
        String regexPattern = regex;
        Pattern p = Pattern.compile(regexPattern);
        Matcher m = p.matcher(text);
        String validationCode = "";
        if (m.find()) {
            validationCode = m.group(0).trim();
        }
        log.info("Get Value with Regex " + regex + " and result: " + validationCode);
        return validationCode;
    }

    @Step
    protected String getCurrentURL() {
        String url = driver.getCurrentUrl();
        log.info("Current URL is " + url);
        return driver.getCurrentUrl();
    }

    @Step
    protected String readFile(String filePath) throws IOException {
        log.info("Read File from: " + filePath);
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

    @Step
    protected void goToWeb(String url) {
        driver.get(url);
        log.info("Landing to Login Page: " + url);
    }

    @Step
    protected void switchToFrameByIndex(int index) {
        driver.switchTo().frame(index);
        log.info("Switch to iframe by index: " + index);
    }

    @Step
    protected void switchToFrameByIdOrName(WebElement element) {
        driver.switchTo().frame(element);
        log.info("Switch to iframe by id or name: " + element);
    }

    @Step
    protected void switchFrameToDefaultContent() {
        driver.switchTo().defaultContent();
        log.info("Switch to default content - Back to main Page");
    }

    @Step
    protected void switchFrameToParentFrame() {
        driver.switchTo().parentFrame();
        log.info("Switch to parent frame");
    }

    @Step
    protected void performKeyboardAction(Keys key) {
        Actions builder = new Actions(driver);
        builder.sendKeys(key).build().perform();
        builder.release().perform();
        log.info("Keyboard Action: "+ key);
    }

    @Step
    protected void performKeyboardInputAction(String text) {
        Actions builder = new Actions(driver);
        builder.sendKeys(text).build().perform();
        builder.release().perform();
        log.info("Keyboard Input Action: "+ text);
    }

}
