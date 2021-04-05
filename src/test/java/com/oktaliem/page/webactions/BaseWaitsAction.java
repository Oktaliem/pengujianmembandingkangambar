package com.oktaliem.page.webactions;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Author : Okta Liem
 */
public class BaseWaitsAction{
    public WebDriver driver;
    public static Logger log = Logger.getLogger("com.oktaliem.automation");

    public BaseWaitsAction(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Wait actions
     */
    @Step
    protected void wait(int miliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("User waits for " + miliseconds + " milliseconds");
    }

    @Step
    protected void waitForElementActionable(WebElement element, int inSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, inSeconds);
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(element),
                ExpectedConditions.elementToBeClickable(element),
                ExpectedConditions.elementToBeSelected(element)));
        log.info(element + " is visible, clickable and selected");
    }

    @Step
    protected void waitWithJSExecutor(int miliseconds) {
        long start = System.currentTimeMillis();
        ((JavascriptExecutor) driver).executeAsyncScript(
                "window.setTimeout(arguments[arguments.length - 1]," + miliseconds + ");");
        String time = String.valueOf(System.currentTimeMillis() - start);
        log.info("Elapsed time: " + time);
    }

    @Step
    protected void robotWaitFor(int miliseconds) throws AWTException {
        Robot robot = new Robot();
        robot.delay(miliseconds);
        log.info("Wait with Robot class for "+miliseconds+" miliseconds");
    }

    @Step
    protected WebElement fluentWait(By by, int timeOut, int polling) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(timeOut, TimeUnit.MILLISECONDS)
                .pollingEvery(polling, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        WebElement element = wait.until(driver -> driver.findElement(by));
        log.info("fluent wait is success waiting for "+element);
        return  element;
    }

    @Step
    protected WebElement fluentWait(WebElement element, int timeOut, int polling) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(timeOut, TimeUnit.MILLISECONDS)
                .pollingEvery(polling, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        WebElement el = wait.until(driver -> element);
        log.info("fluent wait is success waiting for "+el);
        return  el;
    }

    @Step
    protected void waitUntilLocatorIsVisible(WebElement element, int insSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, insSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
        log.info("wait until element: " + element + "is visible");
    }

    @Step
    protected void waitUntilLocatorIsVisible(By by, int insSeconds) {
        WebElement element = driver.findElement(by);
        WebDriverWait wait = new WebDriverWait(driver, insSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        log.info("wait until element: " + element + "is visible");
    }

    @Step
    protected void waitUntilTextIsPresentInLocator(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
        log.info("wait until text in element: " + element + "is present");
    }

    @Step
    protected void waitUntilLocatorIsInvisible(WebElement element, int inSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, inSeconds);
        wait.until(ExpectedConditions.invisibilityOf(element));
        log.info("wait until element: " + element + "is invisible");
    }
}
