package com.oktaliem.page.webactions;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Author : Okta Liem
 */
public class BaseAssertionAction extends BaseWaitsAction {

    public BaseAssertionAction(WebDriver driver) {
        super(driver);
    }

    @Step
    protected void checkIfTextIsExpected(WebElement element, String expected) {
        Assert.assertEquals(element.getText(), expected);
        log.info("Text is expected: " + expected);
    }

    @Step
    protected void checkIfTextIsExpected(By el, String expected) {
        Assert.assertEquals(driver.findElement(el).getText(), expected);
        log.info("Text is expected: " + expected);
    }

    @Step
    protected Boolean checkIfTextIsContains(By el, String partialText) {
        String text = driver.findElement(el).getText();
        if (text.contains(partialText)) {
            log.info("Check if: " + text + " contains " + partialText + " Return TRUE");
            return true;
        } else {
            log.info("Check if: " + text + " doesn't contains " + partialText + " Return FALSE");
            return false;
        }
    }

    @Step
    protected Boolean checkIfTextIsContains(WebElement element, String partialText) {
        String text = element.getText();
        if (text.contains(partialText)) {
            log.info("Check if: " + text + " contains " + partialText + " Return TRUE");
            return true;
        } else {
            log.info("Check if: " + text + " doesn't contains " + partialText + " Return FALSE");
            return false;
        }
    }

    @Step
    protected void checkIfTextIsNotExpected(WebElement element, String expected) {
        String el = element.getText();
        Assert.assertNotEquals(el, expected);
        log.info("PASSED - Text is not expected, " + "expected: " + expected + " and actual: " + el);
    }

    @Step
    protected void checkIfTextIsNotExpected(By el, String expected) {
        String element = driver.findElement(el).getText();
        Assert.assertNotEquals(element, expected);
        log.info("PASSED - Text is not expected, " + "expected: " + expected + " and actual: " + element);
    }

    @Step
    protected boolean checkIfElementIsVisible(WebElement element, int inSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, inSeconds);
            wait.until(ExpectedConditions.visibilityOf(element));
            log.info(element + " is visible");
            return true;
        } catch (Exception e) {
            log.info(element + " is not visible");
            return false;
        }
    }

    @Step
    protected boolean checkIfElementIsPresent(By by, int inSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, inSeconds);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            log.info(by + " is present");
            return true;
        } catch (Exception e) {
            log.info(by + " is not present");
            return false;
        }
    }

    @Step
    protected boolean checkIfElementIsInvisible(WebElement element, int inSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, inSeconds);
            wait.until(ExpectedConditions.invisibilityOf(element));
            log.info(element + " is invisible");
            return true;
        } catch (Exception e) {
            log.info(element + " is not invisible");
            return false;
        }
    }

    @Step
    protected boolean checkIfElementIsClickAble(WebElement element, int inSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, inSeconds);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            log.info(element + " is clickable");
            return true;
        } catch (Exception e) {
            log.info(element + " is not clickable");
            return false;
        }
    }


}
