package com.selenium.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.selenium.utils.ConfigReader;
import com.selenium.utils.DriverFactory;
import com.selenium.utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Base Test class containing common test setup and teardown methods
 */
public class BaseTest {
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    
    // ExtentReports
    protected static ExtentReports extent;
    protected static ExtentTest test;
    
    @BeforeSuite
    public void setUpSuite() {
        logger.info("Setting up test suite");
        setupExtentReports();
    }
    
    @BeforeMethod
    public void setUp(ITestResult result) {
        logger.info("Setting up test: {}", result.getMethod().getMethodName());
        
        // Initialize driver
        DriverFactory.initializeDriver();
        
        // Navigate to application URL
        DriverFactory.getDriver().get(ConfigReader.getUrl());
        logger.info("Navigated to application URL: {}", ConfigReader.getUrl());
        
        // Create test in ExtentReports
        test = extent.createTest(result.getMethod().getMethodName());
        test.info("Test started: " + result.getMethod().getMethodName());
    }
    
    @AfterMethod
    public void tearDown(ITestResult result) {
        logger.info("Tearing down test: {}", result.getMethod().getMethodName());
        
        // Handle test result for ExtentReports
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.error("Test failed: {}", result.getMethod().getMethodName());
            test.fail("Test Failed: " + result.getThrowable().getMessage());
            
            // Take screenshot on failure
            String screenshotPath = ScreenshotUtils.takeScreenshotOnFailure(
                DriverFactory.getDriver(), 
                result.getMethod().getMethodName()
            );
            if (screenshotPath != null) {
                test.addScreenCaptureFromPath(screenshotPath);
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.info("Test passed: {}", result.getMethod().getMethodName());
            test.pass("Test Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.warn("Test skipped: {}", result.getMethod().getMethodName());
            test.skip("Test Skipped: " + result.getThrowable().getMessage());
        }
        
        // Quit driver
        DriverFactory.quitDriver();
    }
    
    @AfterSuite
    public void tearDownSuite() {
        logger.info("Tearing down test suite");
        
        // Flush ExtentReports
        if (extent != null) {
            extent.flush();
        }
    }
    
    /**
     * Setup ExtentReports configuration
     */
    private void setupExtentReports() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        String reportPath = ConfigReader.getReportsPath() + "TestReport_" + timestamp + ".html";
        
        // Create reports directory if it doesn't exist
        File reportDir = new File(ConfigReader.getReportsPath());
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }
        
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Selenium POM Framework Test Report");
        sparkReporter.config().setReportName("Login Test Automation Report");
        
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Browser", ConfigReader.getBrowser());
        extent.setSystemInfo("Application URL", ConfigReader.getUrl());
        
        logger.info("ExtentReports configured. Report will be saved at: {}", reportPath);
    }
    
    /**
     * Log info message to both logger and ExtentReports
     */
    protected void logInfo(String message) {
        logger.info(message);
        if (test != null) {
            test.info(message);
        }
    }
    
    /**
     * Log pass message to both logger and ExtentReports
     */
    protected void logPass(String message) {
        logger.info("PASS: {}", message);
        if (test != null) {
            test.pass(message);
        }
    }
    
    /**
     * Log fail message to both logger and ExtentReports
     */
    protected void logFail(String message) {
        logger.error("FAIL: {}", message);
        if (test != null) {
            test.fail(message);
        }
    }
}
