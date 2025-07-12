package com.selenium.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

/**
 * Simple WebDriver Factory - manages WebDriver creation and cleanup
 */
public class SimpleDriverFactory {
    private static WebDriver driver;

    /**
     * Initialize Chrome WebDriver with basic configuration
     */
    public static void initializeDriver() {
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--window-size=1920,1080");
        
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        
        System.out.println("WebDriver initialized successfully");
    }

    /**
     * Get the current WebDriver instance
     */
    public static WebDriver getDriver() {
        return driver;
    }

    /**
     * Quit the WebDriver and cleanup
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("WebDriver quit successfully");
        }
    }
}
