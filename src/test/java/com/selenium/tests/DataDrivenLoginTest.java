package com.selenium.tests;

import com.selenium.base.BaseTest;
import com.selenium.dataproviders.LoginDataProvider;
import com.selenium.pages.DashboardPage;
import com.selenium.pages.LoginPage;
import com.selenium.utils.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Data-driven login tests using TestNG Data Providers
 */
public class DataDrivenLoginTest extends BaseTest {

    @Test(dataProvider = "validLoginData", dataProviderClass = LoginDataProvider.class,
          description = "Test successful login with valid credentials from data provider")
    public void testValidLoginWithDataProvider(String username, String password) {
        logInfo("Starting data-driven test: Valid Login with username: " + username);
        
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        DashboardPage dashboardPage = new DashboardPage(DriverFactory.getDriver());
        
        // Perform login
        loginPage.login(username, password);
        
        // Verify successful login
        Assert.assertTrue(dashboardPage.isDashboardPageLoaded(), 
                         "Dashboard should be loaded for valid credentials");
        logPass("Successfully logged in with username: " + username);
    }

    @Test(dataProvider = "invalidLoginData", dataProviderClass = LoginDataProvider.class,
          description = "Test login failure with invalid credentials from data provider")
    public void testInvalidLoginWithDataProvider(String username, String password, String expectedError) {
        logInfo("Starting data-driven test: Invalid Login with username: " + username);
        
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        
        // Perform login with invalid credentials
        loginPage.login(username, password);
        
        // Verify error message is displayed
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "Error message should be displayed for invalid credentials");
        
        // Verify error message content
        String actualError = loginPage.getErrorMessage();
        Assert.assertTrue(actualError.contains(expectedError.split(" ")[0]), 
                         "Error message should contain expected text");
        logPass("Error message verified for username: " + username);
    }

    @Test(dataProvider = "specialCharacterData", dataProviderClass = LoginDataProvider.class,
          description = "Test login with special characters in credentials")
    public void testLoginWithSpecialCharacters(String username, String password, String expectedError) {
        logInfo("Starting test: Login with special characters - username: " + username);
        
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        
        // Perform login with special character credentials
        loginPage.login(username, password);
        
        // Verify error handling
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "Error message should be displayed for special character credentials");
        logPass("Special character handling verified for username: " + username);
    }

    @Test(dataProvider = "lockedOutUserData", dataProviderClass = LoginDataProvider.class,
          description = "Test login with locked out user")
    public void testLockedOutUser(String username, String password, String expectedError) {
        logInfo("Starting test: Locked out user login - username: " + username);
        
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        
        // Attempt login with locked out user
        loginPage.login(username, password);
        
        // Verify locked out error message
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                         "Error message should be displayed for locked out user");
        
        String actualError = loginPage.getErrorMessage();
        Assert.assertTrue(actualError.contains("locked out"), 
                         "Error message should indicate user is locked out");
        logPass("Locked out user error message verified");
    }

    @Test(dataProvider = "problemUserData", dataProviderClass = LoginDataProvider.class,
          description = "Test login with problem users")
    public void testProblemUsers(String username, String password) {
        logInfo("Starting test: Problem user login - username: " + username);
        
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        DashboardPage dashboardPage = new DashboardPage(DriverFactory.getDriver());
        
        // Perform login with problem user
        loginPage.login(username, password);
        
        // Verify login (problem users can still login but may have UI issues)
        Assert.assertTrue(dashboardPage.isDashboardPageLoaded(), 
                         "Dashboard should be loaded even for problem users");
        logPass("Problem user login successful - username: " + username);
        
        // Log out for cleanup
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isLoginPageLoaded(), 
                         "Should return to login page after logout");
        logPass("Successfully logged out problem user");
    }
}
