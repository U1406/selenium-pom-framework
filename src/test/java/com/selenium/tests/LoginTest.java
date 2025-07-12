package com.selenium.tests;

import com.selenium.base.BaseTest;
import com.selenium.pages.DashboardPage;
import com.selenium.pages.LoginPage;
import com.selenium.utils.ConfigReader;
import com.selenium.utils.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Login Test class containing all login-related test scenarios
 */
public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        logInfo("Starting test: Successful Login with Valid Credentials");
        
        // Initialize page objects
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        DashboardPage dashboardPage = new DashboardPage(DriverFactory.getDriver());
        
        // Verify login page is loaded
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page should be loaded");
        logPass("Login page loaded successfully");
        
        // Verify login logo is displayed
        Assert.assertTrue(loginPage.isLoginLogoDisplayed(), "Login logo should be displayed");
        logPass("Login logo is displayed");
        
        // Perform login with valid credentials
        String validUsername = ConfigReader.getValidUsername();
        String validPassword = ConfigReader.getValidPassword();
        
        logInfo("Logging in with username: " + validUsername);
        loginPage.login(validUsername, validPassword);
        
        // Verify successful login by checking dashboard page
        Assert.assertTrue(dashboardPage.isDashboardPageLoaded(), "Dashboard page should be loaded after successful login");
        logPass("Successfully logged in and navigated to dashboard");
        
        // Verify dashboard elements
        Assert.assertTrue(dashboardPage.isAppLogoDisplayed(), "App logo should be displayed on dashboard");
        Assert.assertTrue(dashboardPage.isShoppingCartDisplayed(), "Shopping cart should be displayed on dashboard");
        Assert.assertTrue(dashboardPage.areInventoryItemsDisplayed(), "Inventory items should be displayed on dashboard");
        logPass("All dashboard elements are displayed correctly");
        
        // Verify page title
        String expectedTitle = "Products";
        String actualTitle = dashboardPage.getPageTitleText();
        Assert.assertEquals(actualTitle, expectedTitle, "Dashboard page title should be 'Products'");
        logPass("Dashboard page title verified: " + actualTitle);
        
        logInfo("Test completed: Successful Login with Valid Credentials");
    }

    @Test(priority = 2, description = "Verify login failure with invalid username")
    public void testLoginWithInvalidUsername() {
        logInfo("Starting test: Login with Invalid Username");
        
        // Initialize page objects
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        
        // Verify login page is loaded
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page should be loaded");
        logPass("Login page loaded successfully");
        
        // Attempt login with invalid username
        String invalidUsername = ConfigReader.getInvalidUsername();
        String validPassword = ConfigReader.getValidPassword();
        
        logInfo("Attempting login with invalid username: " + invalidUsername);
        loginPage.login(invalidUsername, validPassword);
        
        // Verify error message is displayed
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for invalid username");
        logPass("Error message displayed for invalid username");
        
        // Verify error message content
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Username and password do not match"), 
                         "Error message should indicate credentials mismatch");
        logPass("Error message content verified: " + errorMessage);
        
        // Verify user remains on login page
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "User should remain on login page after failed login");
        logPass("User remained on login page after failed login attempt");
        
        logInfo("Test completed: Login with Invalid Username");
    }

    @Test(priority = 3, description = "Verify login failure with invalid password")
    public void testLoginWithInvalidPassword() {
        logInfo("Starting test: Login with Invalid Password");
        
        // Initialize page objects
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        
        // Verify login page is loaded
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page should be loaded");
        logPass("Login page loaded successfully");
        
        // Attempt login with invalid password
        String validUsername = ConfigReader.getValidUsername();
        String invalidPassword = ConfigReader.getInvalidPassword();
        
        logInfo("Attempting login with invalid password for username: " + validUsername);
        loginPage.login(validUsername, invalidPassword);
        
        // Verify error message is displayed
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for invalid password");
        logPass("Error message displayed for invalid password");
        
        // Verify error message content
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Username and password do not match"), 
                         "Error message should indicate credentials mismatch");
        logPass("Error message content verified: " + errorMessage);
        
        // Verify user remains on login page
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "User should remain on login page after failed login");
        logPass("User remained on login page after failed login attempt");
        
        logInfo("Test completed: Login with Invalid Password");
    }

    @Test(priority = 4, description = "Verify login failure with empty credentials")
    public void testLoginWithEmptyCredentials() {
        logInfo("Starting test: Login with Empty Credentials");
        
        // Initialize page objects
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        
        // Verify login page is loaded
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page should be loaded");
        logPass("Login page loaded successfully");
        
        // Attempt login with empty credentials
        logInfo("Attempting login with empty credentials");
        loginPage.login("", "");
        
        // Verify error message is displayed
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for empty credentials");
        logPass("Error message displayed for empty credentials");
        
        // Verify error message content
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Username is required"), 
                         "Error message should indicate username is required");
        logPass("Error message content verified: " + errorMessage);
        
        // Verify user remains on login page
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "User should remain on login page after failed login");
        logPass("User remained on login page after failed login attempt");
        
        logInfo("Test completed: Login with Empty Credentials");
    }

    @Test(priority = 5, description = "Verify complete login flow with logout")
    public void testCompleteLoginLogoutFlow() {
        logInfo("Starting test: Complete Login-Logout Flow");
        
        // Initialize page objects
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        DashboardPage dashboardPage = new DashboardPage(DriverFactory.getDriver());
        
        // Perform successful login
        String validUsername = ConfigReader.getValidUsername();
        String validPassword = ConfigReader.getValidPassword();
        
        logInfo("Performing login with valid credentials");
        loginPage.login(validUsername, validPassword);
        
        // Verify successful login
        Assert.assertTrue(dashboardPage.isDashboardPageLoaded(), "Dashboard page should be loaded after login");
        logPass("Successfully logged in");
        
        // Perform logout
        logInfo("Performing logout");
        dashboardPage.logout();
        
        // Verify logout - should be back to login page
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Should be redirected to login page after logout");
        logPass("Successfully logged out and redirected to login page");
        
        // Verify login form is cleared/reset
        loginPage.clearLoginForm();
        logPass("Login form cleared after logout");
        
        logInfo("Test completed: Complete Login-Logout Flow");
    }
}
