package com.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Login Page Object Model class
 */
public class LoginPage extends BasePage {

    // Page Elements
    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    @FindBy(className = "login_logo")
    private WebElement loginLogo;

    @FindBy(css = ".login_credentials")
    private WebElement credentialsInfo;

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    /**
     * Enter username in the username field
     */
    public LoginPage enterUsername(String username) {
        sendKeys(usernameField, username);
        return this;
    }

    /**
     * Enter password in the password field
     */
    public LoginPage enterPassword(String password) {
        sendKeys(passwordField, password);
        return this;
    }

    /**
     * Click on the login button
     */
    public void clickLoginButton() {
        clickElement(loginButton);
    }

    /**
     * Perform complete login action
     */
    public void login(String username, String password) {
        logger.info("Attempting to login with username: {}", username);
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    /**
     * Get error message text
     */
    public String getErrorMessage() {
        if (isElementDisplayed(errorMessage)) {
            return getText(errorMessage);
        }
        return null;
    }

    /**
     * Check if error message is displayed
     */
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    /**
     * Check if login logo is displayed
     */
    public boolean isLoginLogoDisplayed() {
        return isElementDisplayed(loginLogo);
    }

    /**
     * Check if login page is loaded
     */
    public boolean isLoginPageLoaded() {
        return isElementDisplayed(usernameField) && 
               isElementDisplayed(passwordField) && 
               isElementDisplayed(loginButton);
    }

    /**
     * Clear login form
     */
    public void clearLoginForm() {
        usernameField.clear();
        passwordField.clear();
        logger.info("Login form cleared");
    }

    /**
     * Get credentials information text
     */
    public String getCredentialsInfo() {
        if (isElementDisplayed(credentialsInfo)) {
            return getText(credentialsInfo);
        }
        return null;
    }
}
