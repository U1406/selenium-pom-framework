package com.selenium.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for taking and managing screenshots
 */
public class ScreenshotUtils {
    private static final Logger logger = LogManager.getLogger(ScreenshotUtils.class);

    /**
     * Take a screenshot and save it to the specified path
     * @param driver WebDriver instance
     * @param testName Name of the test for screenshot naming
     * @return Path to the saved screenshot
     */
    public static String takeScreenshot(WebDriver driver, String testName) {
        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String fileName = testName + "_" + timestamp + ".png";
            String screenshotPath = ConfigReader.getScreenshotsPath() + fileName;
            
            File destFile = new File(screenshotPath);
            destFile.getParentFile().mkdirs(); // Create directories if they don't exist
            
            FileUtils.copyFile(sourceFile, destFile);
            logger.info("Screenshot saved: {}", screenshotPath);
            
            return screenshotPath;
        } catch (IOException e) {
            logger.error("Failed to take screenshot: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Take a screenshot on test failure
     * @param driver WebDriver instance
     * @param testName Name of the failed test
     * @return Path to the saved screenshot
     */
    public static String takeScreenshotOnFailure(WebDriver driver, String testName) {
        return takeScreenshot(driver, testName + "_FAILED");
    }
}
