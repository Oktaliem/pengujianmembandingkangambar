package com.oktaliem.page.webactions;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

import java.util.List;

/**
 * Author : Okta Liem
 */
public class BaseActions extends BaseGeneralAction{

    public BaseActions(WebDriver driver) {
        super(driver);
    }

    /**
     * Pure Page Object for Page Actions
     */

    @Step
    protected void clickOn(By el) {
        WebElement element = driver.findElement(el);
        try {
            element.click();
            log.info("User clicks On Element: " + element);
        } catch (ElementClickInterceptedException e) {
            clickElementViaJSExecutor(element);
        }
    }

    @Step
    protected void inputTextBox(By el, String value) {
        WebElement element = driver.findElement(el);
        element.clear();
        element.sendKeys(value);
        log.info("User inputs field with element: " + el + " and value " + value);
    }

    @Step
    protected String getTextFromElement(By el) {
        String text = driver.findElement(el).getText();
        log.info("Get Text with value: " + text);
        return text;
    }

    @Step
    protected void selectOnDropDownListByText(By el, String text) {
        try {
            WebElement element = driver.findElement(el);
            waitUntilLocatorIsVisible(element, 10);
            Select select = new Select(element);
            select.selectByVisibleText(text);
            log.info("Select Drop down List Element by visible text : " + element);
        } catch (UnexpectedTagNameException e) {
            List<WebElement> elements = driver.findElements(el);
            for (WebElement element : elements) {
                if (element.getText().trim().equals(text.trim())) {
                    clickOn(element);
                    log.info("Select Drop down List Element by visible text : " + text);
                    break;
                }
            }
        }
    }

    @Step
    protected String getDropDownListValueDisplayed(By el) {
        WebElement element = driver.findElement(el);
        waitUntilLocatorIsVisible(element, 10);
        Select select = new Select(element);
        String value = select.getFirstSelectedOption().getText();
        log.info("Drop down list text displayed : " + value);
        return value;
    }

    @Step
    protected void selectOnDropDownListByValue(By el, String value) {
        WebElement element = driver.findElement(el);
        waitUntilLocatorIsVisible(element, 10);
        Select select = new Select(element);
        select.selectByValue(value);
        log.info("Select Drop down List Element by visible text : " + element);
    }

    @Step
    protected void selectOnRadioButtonByText(By els, String text) {
        for (WebElement element : driver.findElements(els)) {
            if (element.getText().equals(text)) {
                clickOn(element);
                log.info("Select radio button by text: " + text);
                break;
            }
        }
    }

    @Step
    protected void selectOnRadioButtonByValue(By els, String text) {
        for (WebElement element : driver.findElements(els)) {
            if (element.getAttribute("value").trim().equals(text.trim())) {
                clickOn(element);
                log.info("Select radio button by value: " + text);
                break;
            }
        }
    }

    @Step
    protected void selectCheckBox(By el, String status, int index) {
        WebElement element = driver.findElements(el).get(index);
        waitUntilLocatorIsVisible(element, 10);
        if (status.equals("n")) {
            if (element.isSelected()) {
                clickOn(element);
            } else {
                log.info("check box is disabled by default");
            }
        }
        if (status.equals("y")) {
            if (element.isSelected()) {
                log.info("check box is already enabled");
            } else {
                clickOn(element);
            }
        }
    }

    @Step
    protected void uploadFile(By el, String fileName) {
        WebElement element = driver.findElement(el);
        waitUntilLocatorIsVisible(element, 10);
        element.sendKeys(System.getProperty("user.dir") + "/src/main/resources/" + fileName);
        log.info("Choose file name: " + fileName);
    }

    @Step
    protected WebElement findElementByDoubleChain(By el1, By el2) {
        WebElement els = driver.findElement(el1).findElement(el2);
        log.info("Find element by: " + el1 + " and " + el2);
        return els;
    }

    /**
     * Page Factory for Page Actions
     */
    @Step
    protected void clickOn(WebElement element) {
        try {
            element.click();
            log.info("User clicks On Element: " + element);
        } catch (Exception e) {
            System.out.println(e);
            clickElementViaJSExecutor(element);
        }
    }

    @Step
    protected void inputTextBox(WebElement element, String value) {
        try { element.clear();
        } catch (ElementNotInteractableException e) { log.warn(e); }
        element.sendKeys(value);
        log.info("User inputs field with element: " + element + " and value " + value);
    }

    @Step
    protected String getTextFromElement(WebElement element) {
        waitUntilLocatorIsVisible(element, 10);
        String text = element.getText();
        log.info("Get Text with value: " + text);
        return text;

    }

    @Step
    protected void selectOnDropDownListByText(WebElement element, String text) {
        waitUntilLocatorIsVisible(element, 10);
        Select select = new Select(element);
        select.selectByVisibleText(text);
        log.info("Select Drop down List Element by visible text : " + element);
    }

    @Step
    protected String getDropDownListValueDisplayed(WebElement element) {
        waitUntilLocatorIsVisible(element, 10);
        Select select = new Select(element);
        String value = select.getFirstSelectedOption().getText();
        log.info("Drop down list text displayed : " + value);
        return value;
    }

    @Step
    protected void selectOnDropDownListByText(List<WebElement> elements, String text) {
        for (WebElement element : elements) {
            if (element.getText().trim().equals(text.trim())) {
                clickOn(element);
                log.info("Select Drop down List Element by visible text : " + text);
                break;
            }
        }
    }

    @Step
    protected void selectOnDropDownListByValue(WebElement element, String value) {
        waitUntilLocatorIsVisible(element, 10);
        Select select = new Select(element);
        select.selectByValue(value);
        log.info("Select Drop down List Element by visible text : " + element);
    }

    @Step
    protected void selectOnRadioButtonByText(List<WebElement> elements, String text) {
        for (WebElement element : elements) {
            System.out.println("Text: " + element.getText());
            if (element.getText().trim().equals(text.trim())) {
                clickOn(element);
                log.info("Select radio button by text: " + text);
                break;
            }
        }
    }

    @Step
    protected void selectOnRadioButtonByValue(List<WebElement> elements, String text) {
        for (WebElement element : elements) {
            if (element.getAttribute("value").trim().equals(text.trim())) {
                clickOn(element);
                log.info("Select radio button by value: " + text);
                break;
            }
        }
    }

    @Step
    protected void selectCheckBox(WebElement element, String status) {
        waitUntilLocatorIsVisible(element, 10);
        if (status.equals("n")) {
            if (element.isSelected()) {
                clickOn(element);
            } else {
                log.info("check box is disabled by default");
            }
        }
        if (status.equals("y")) {
            if (element.isSelected()) {
                log.info("check box is already enabled");
            } else {
                clickOn(element);
            }
        }
    }

    @Step
    protected void uploadFile(WebElement element, String fileName) {
        waitUntilLocatorIsVisible(element, 10);
        try {
            if (driver instanceof RemoteWebDriver) {
                ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
            }
        } catch (WebDriverException e) {
        }
        element.sendKeys(System.getProperty("user.dir") + "/src/main/resources/" + fileName);
        log.info("Choose file name: " + fileName);
    }

}
