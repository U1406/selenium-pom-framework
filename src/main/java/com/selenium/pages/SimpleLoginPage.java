package com.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Simple Login Page - demonstrates POM pattern
 */
public class SimpleLoginPage extends SimpleBasePage {

    // Page Elements using @FindBy annotations
    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    // Constructor
    public SimpleLoginPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    public SimpleLoginPage enterUsername(String username) {
        sendKeys(usernameField, username);
        return this;
    }

    public SimpleLoginPage enterPassword(String password) {
        sendKeys(passwordField, password);
        return this;
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        return isDisplayed(errorMessage) ? getText(errorMessage) : null;
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }
}
