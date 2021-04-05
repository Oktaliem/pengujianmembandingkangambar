package com.oktaliem.page.webactions;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Author : Okta Liem
 */
public class BaseJSExecutorAction extends BaseAssertionAction{

    public BaseJSExecutorAction(WebDriver driver) {
        super(driver);
    }

    @Step
    protected void openNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');");
        log.info("Open New Tab");
    }

    @Step
    protected void handleJavascriptPopUp(String info) {
        try {
            org.openqa.selenium.Alert alert = driver.switchTo().alert();
            if (alert.getText().equals(info)) {
                alert.accept();
            } else {
                alert.dismiss();
            }
        } catch (Exception e) {
        }
        log.info("accepting javascript Pop Up");
    }

    @Step
    protected void scrollToBottomPage() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String query = "window.scrollTo(0,document.body.scrollHeight);";
        jsExecutor.executeScript(query);
        log.info("Scroll to the buttom page");
    }

    @Step
    protected void scrollToTopPage() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String query = "window.scrollTo(0,-document.body.scrollHeight);";
        jsExecutor.executeScript(query);
        log.info("Scroll to the top page");
    }
    @Step
    protected void scrollUntilViewElement(By el) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(el);
        String query = "arguments[0].scrollIntoView(true);";
        jsExecutor.executeScript(query, element);
        log.info("Scroll until element: " + element + " is displayed");
    }

    @Step
    protected void scrollUntilViewElement(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String query = "arguments[0].scrollIntoView(true);";
        jsExecutor.executeScript(query, element);
        log.info("Scroll until element: " + element + " is displayed");
    }

    @Step
    protected void refreshPageViaJSExecutor() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("history.go(0)");
        log.info("refresh Page Via JavaScriptExecutor");
    }

    @Step
    protected Object executeViaJSExecutor(String javascript) {
        log.info("execute javascript: " + javascript);
        return ((JavascriptExecutor) driver).executeScript(javascript);
    }

    @Step
    protected WebElement findElementByJSExecutor(String by, String element, int index) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement el;
        switch (by) {
            case "id":
                el = (WebElement) jsExecutor.executeScript("return document.getElementById('" + element + "')");
                log.info("return document.getElementById('" + element + "')" + " Succeed");
                break;
            case "tagName":
                el = (WebElement) jsExecutor.executeScript("return document.getElementsByTagName('" + element + "')" +
                        "[" + index + "]");
                log.info("return document.getElementBysTagName('" + element + "')[" + index + "]" + " Succeed");
                break;
            case "class":
                el = (WebElement) jsExecutor.executeScript("return document.getElementsByClassName('" + element + "')" +
                        "[" + index + "]");
                log.info("return document.getElementsByClassName('" + element + "')[" + index + "]" + " Succeed");
                break;
            case "name":
                el = (WebElement) jsExecutor.executeScript("return document.getElementsByName('" + element + "')" +
                        "[" + index + "]");
                log.info("return document.getElementsByName('" + element + "')[" + index + "]" + " Succeed");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + element);
        }
        return el;
    }

    @Step
    protected String getTextElementViaJSExecutor(String by, String element, int index) {
        WebElement el = findElementByJSExecutor(by, element, index);
        String text = el.getText();
        log.info("get text By: " + by + "element: " + element + " with value " + text);
        return text;
    }

    @Step
    protected String getAttributeViaJSExecutor(String by, String element, int index, String attribute) {
        WebElement el = findElementByJSExecutor(by, element, index);
        String text = el.getAttribute(attribute);
        log.info("get attribute " + attribute + " By: " + by + " element: " + element + " with value " + text);
        return text;
    }

    @Step
    protected String getURLByJSExecutor() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String text = jsExecutor.executeScript("return document.URL;").toString();
        log.info("get url " + text);
        return text;
    }

    @Step
    protected void navigateViaJSExecutor(String url) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location = '" + url + "'");
        log.info("navigate via JSExecutor to " + url);
    }

    @Step
    protected void highlightElement(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    @Step
    protected void inputTextByJSExecutor(WebElement element, String text) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].value='" + text + "'", element);
        log.info("input text in " + element + " with value " + text);
    }

    @Step
    protected void clickElementViaJSExecutor(WebElement element) {
        log.warn("Element is not clickable, try to click with JSExecutor");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", element);
        log.info("click on " + element + " via JSExecutor succeed");
    }

    @Step
    protected void zoomPage(String value) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("document.body.style.zoom='" + value + "'");
    }

    @Step
    protected void changeTextLabel(String text, WebElement element){
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].innerText='"+text+"'", element);
    }

}
