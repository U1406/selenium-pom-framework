package com.selenium.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.util.List;

/**
 * Common utility methods for web interactions
 */
public class WebUtils {
    private static final Logger logger = LogManager.getLogger(WebUtils.class);
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private JavascriptExecutor jsExecutor;

    public WebUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        this.actions = new Actions(driver);
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    /**
     * Scroll to element using JavaScript
     */
    public void scrollToElement(WebElement element) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        logger.info("Scrolled to element: {}", element.toString());
    }

    /**
     * Highlight element for debugging
     */
    public void highlightElement(WebElement element) {
        jsExecutor.executeScript("arguments[0].style.border='3px solid red'", element);
        logger.info("Highlighted element: {}", element.toString());
    }

    /**
     * Click element using JavaScript
     */
    public void clickUsingJS(WebElement element) {
        jsExecutor.executeScript("arguments[0].click();", element);
        logger.info("Clicked element using JavaScript: {}", element.toString());
    }

    /**
     * Select dropdown option by visible text
     */
    public void selectByVisibleText(WebElement dropdown, String text) {
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
        logger.info("Selected option '{}' from dropdown", text);
    }

    /**
     * Select dropdown option by value
     */
    public void selectByValue(WebElement dropdown, String value) {
        Select select = new Select(dropdown);
        select.selectByValue(value);
        logger.info("Selected option with value '{}' from dropdown", value);
    }

    /**
     * Get all options from dropdown
     */
    public List<WebElement> getAllDropdownOptions(WebElement dropdown) {
        Select select = new Select(dropdown);
        List<WebElement> options = select.getOptions();
        logger.info("Retrieved {} options from dropdown", options.size());
        return options;
    }

    /**
     * Hover over element
     */
    public void hoverOverElement(WebElement element) {
        actions.moveToElement(element).perform();
        logger.info("Hovered over element: {}", element.toString());
    }

    /**
     * Double click element
     */
    public void doubleClick(WebElement element) {
        actions.doubleClick(element).perform();
        logger.info("Double clicked element: {}", element.toString());
    }

    /**
     * Right click element
     */
    public void rightClick(WebElement element) {
        actions.contextClick(element).perform();
        logger.info("Right clicked element: {}", element.toString());
    }

    /**
     * Wait for element to be present
     */
    public WebElement waitForElementPresent(org.openqa.selenium.By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Wait for element to be clickable
     */
    public WebElement waitForElementClickable(org.openqa.selenium.By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait for text to be present in element
     */
    public boolean waitForTextToBePresentInElement(WebElement element, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    /**
     * Check if element is present
     */
    public boolean isElementPresent(org.openqa.selenium.By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Get element attribute value
     */
    public String getElementAttribute(WebElement element, String attribute) {
        String value = element.getAttribute(attribute);
        logger.info("Retrieved attribute '{}' value: '{}'", attribute, value);
        return value;
    }

    /**
     * Clear and send keys to element
     */
    public void clearAndSendKeys(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
        logger.info("Cleared and entered text '{}' in element", text);
    }

    /**
     * Switch to frame by element
     */
    public void switchToFrame(WebElement frameElement) {
        driver.switchTo().frame(frameElement);
        logger.info("Switched to frame");
    }

    /**
     * Switch to default content
     */
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
        logger.info("Switched to default content");
    }

    /**
     * Accept alert
     */
    public void acceptAlert() {
        driver.switchTo().alert().accept();
        logger.info("Accepted alert");
    }

    /**
     * Dismiss alert
     */
    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
        logger.info("Dismissed alert");
    }

    /**
     * Get alert text
     */
    public String getAlertText() {
        String alertText = driver.switchTo().alert().getText();
        logger.info("Retrieved alert text: '{}'", alertText);
        return alertText;
    }
}
