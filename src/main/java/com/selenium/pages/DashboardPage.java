package com.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Dashboard/Home Page Object Model class
 */
public class DashboardPage extends BasePage {

    // Page Elements
    @FindBy(className = "app_logo")
    private WebElement appLogo;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "inventory_list")
    private WebElement inventoryList;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartLink;

    @FindBy(css = ".inventory_item")
    private WebElement firstInventoryItem;

    // Constructor
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    /**
     * Check if dashboard page is loaded
     */
    public boolean isDashboardPageLoaded() {
        return isElementDisplayed(appLogo) && 
               isElementDisplayed(inventoryList) && 
               getCurrentUrl().contains("inventory");
    }

    /**
     * Get page title text
     */
    public String getPageTitleText() {
        if (isElementDisplayed(pageTitle)) {
            return getText(pageTitle);
        }
        return null;
    }

    /**
     * Click on menu button
     */
    public void clickMenuButton() {
        clickElement(menuButton);
    }

    /**
     * Logout from the application
     */
    public void logout() {
        logger.info("Logging out from the application");
        clickMenuButton();
        clickElement(logoutLink);
    }

    /**
     * Check if app logo is displayed
     */
    public boolean isAppLogoDisplayed() {
        return isElementDisplayed(appLogo);
    }

    /**
     * Check if shopping cart is displayed
     */
    public boolean isShoppingCartDisplayed() {
        return isElementDisplayed(shoppingCartLink);
    }

    /**
     * Check if inventory items are displayed
     */
    public boolean areInventoryItemsDisplayed() {
        return isElementDisplayed(inventoryList) && isElementDisplayed(firstInventoryItem);
    }

    /**
     * Get current dashboard URL
     */
    public String getDashboardUrl() {
        return getCurrentUrl();
    }
}
