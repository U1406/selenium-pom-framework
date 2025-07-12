package com.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Simple Dashboard Page - represents the page after successful login
 */
public class SimpleDashboardPage extends SimpleBasePage {

    // Page Elements
    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "inventory_list")
    private WebElement inventoryList;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    // Constructor
    public SimpleDashboardPage(WebDriver driver) {
        super(driver);
    }

    // Page Actions
    public boolean isDashboardLoaded() {
        return isDisplayed(pageTitle) && isDisplayed(inventoryList);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public void logout() {
        click(menuButton);
        click(logoutLink);
    }
}
