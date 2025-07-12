package com.selenium.tests;

import com.selenium.pages.SimpleDashboardPage;
import com.selenium.pages.SimpleLoginPage;
import com.selenium.utils.SimpleDriverFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Simple Login Tests - demonstrates basic POM testing approach
 */
public class SimpleLoginTest {

    @BeforeMethod
    public void setUp() {
        SimpleDriverFactory.initializeDriver();
        SimpleDriverFactory.getDriver().get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown() {
        SimpleDriverFactory.quitDriver();
    }

    @Test
    public void testValidLogin() {
        System.out.println("Testing valid login...");
        
        // Create page objects
        SimpleLoginPage loginPage = new SimpleLoginPage(SimpleDriverFactory.getDriver());
        SimpleDashboardPage dashboardPage = new SimpleDashboardPage(SimpleDriverFactory.getDriver());
        
        // Perform login
        loginPage.login("standard_user", "secret_sauce");
        
        // Verify successful login
        Assert.assertTrue(dashboardPage.isDashboardLoaded(), "Dashboard should be loaded");
        Assert.assertEquals(dashboardPage.getPageTitle(), "Products", "Page title should be 'Products'");
        
        System.out.println("Valid login test passed!");
    }

    @Test
    public void testInvalidLogin() {
        System.out.println("Testing invalid login...");
        
        // Create page object
        SimpleLoginPage loginPage = new SimpleLoginPage(SimpleDriverFactory.getDriver());
        
        // Perform login with invalid credentials
        loginPage.login("invalid_user", "invalid_password");
        
        // Verify error message
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message should be displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("do not match"), 
                         "Error message should indicate credential mismatch");
        
        System.out.println("Invalid login test passed!");
    }

    @Test
    public void testEmptyCredentials() {
        System.out.println("Testing empty credentials...");
        
        // Create page object
        SimpleLoginPage loginPage = new SimpleLoginPage(SimpleDriverFactory.getDriver());
        
        // Attempt login with empty credentials
        loginPage.clickLogin();
        
        // Verify error message
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message should be displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"), 
                         "Error message should indicate username is required");
        
        System.out.println("Empty credentials test passed!");
    }
}
