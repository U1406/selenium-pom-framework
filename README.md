# Selenium POM Framework

A comprehensive Selenium WebDriver automation framework built using Java, TestNG, and the Page Object Model (POM) design pattern for testing login scenarios.

🔗 **GitHub Repository**: Ready for CI/CD integration with GitHub Actions

## 🚀 Features

- **Page Object Model (POM)**: Clean separation of page elements and test logic
- **Cross-Browser Support**: Chrome, Firefox, and Edge browsers
- **Parallel Execution**: TestNG support for parallel test execution
- **Extensive Reporting**: ExtentReports with screenshots on failure
- **Logging**: Log4j2 for comprehensive logging
- **Configuration Management**: Properties-based configuration
- **Screenshot Capture**: Automatic screenshots on test failures
- **WebDriver Management**: Automatic driver management using WebDriverManager

## 📁 Project Structure

```
selenium-pom-framework/
├── src/
│   ├── main/java/com/selenium/
│   │   ├── pages/
│   │   │   ├── BasePage.java          # Base page with common methods
│   │   │   ├── LoginPage.java         # Login page object
│   │   │   └── DashboardPage.java     # Dashboard page object
│   │   └── utils/
│   │       ├── ConfigReader.java      # Configuration properties reader
│   │       ├── DriverFactory.java     # WebDriver factory and management
│   │       └── ScreenshotUtils.java   # Screenshot utility methods
│   └── test/
│       ├── java/com/selenium/
│       │   ├── base/
│       │   │   └── BaseTest.java      # Base test class with setup/teardown
│       │   └── tests/
│       │       └── LoginTest.java     # Login test scenarios
│       └── resources/
│           ├── config.properties      # Test configuration
│           ├── testng.xml            # TestNG suite configuration
│           └── log4j2.xml            # Logging configuration
├── test-output/                      # Generated reports and screenshots
│   ├── reports/                      # ExtentReports HTML reports
│   ├── screenshots/                  # Test failure screenshots
│   └── logs/                         # Application logs
└── pom.xml                          # Maven dependencies and configuration
```

## 🛠️ Technologies Used

- **Java 11**: Programming language
- **Selenium WebDriver 4.15.0**: Web automation framework
- **TestNG 7.8.0**: Testing framework
- **Maven**: Build and dependency management
- **ExtentReports 5.1.1**: HTML reporting
- **Log4j2 2.21.1**: Logging framework
- **WebDriverManager 5.6.2**: Automatic driver management

## 📋 Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- Chrome/Firefox/Edge browser installed

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone <repository-url>
cd selenium-pom-framework
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Configure Test Parameters
Edit `src/test/resources/config.properties` to customize:
- Browser type (chrome/firefox/edge)
- Test URLs and credentials
- Timeouts and wait times

### 4. Run Tests

#### Run All Tests
```bash
mvn test
```

#### Run Specific Test Class
```bash
mvn test -Dtest=LoginTest
```

#### Run with Different Browser
```bash
mvn test -Dbrowser=firefox
```

#### Run in Headless Mode
```bash
mvn test -Dheadless=true
```

## 🧪 Test Scenarios

The framework includes comprehensive login test scenarios:

1. **Successful Login**: Valid username and password
2. **Invalid Username**: Test with incorrect username
3. **Invalid Password**: Test with incorrect password
4. **Empty Credentials**: Test with empty fields
5. **Complete Flow**: Login and logout functionality

## 📊 Reporting

### ExtentReports
- HTML reports generated in `test-output/reports/`
- Includes test execution details, screenshots, and system information
- Automatic timestamp-based report naming

### Screenshots
- Automatic screenshot capture on test failures
- Stored in `test-output/screenshots/`
- Linked to ExtentReports for easy viewing

### Logs
- Comprehensive logging using Log4j2
- Console and file logging
- Logs stored in `test-output/logs/`

## ⚙️ Configuration

### Browser Configuration
```properties
# Browser settings
browser=chrome
headless=false
implicit.wait=10
explicit.wait=15
page.load.timeout=30
```

### Test Data Configuration
```properties
# Application settings
app.url=https://www.saucedemo.com/
valid.username=standard_user
valid.password=secret_sauce
```

## 🔧 Customization

### Adding New Pages
1. Create new page class extending `BasePage`
2. Define page elements using `@FindBy` annotations
3. Implement page-specific actions and verifications

### Adding New Tests
1. Create test class extending `BaseTest`
2. Use page objects for interactions
3. Include proper assertions and logging

### Custom Configuration
- Modify `config.properties` for environment-specific settings
- Update `testng.xml` for test suite configuration
- Adjust `log4j2.xml` for logging preferences

## 🐛 Troubleshooting

### Common Issues

1. **WebDriver Issues**
   - Ensure correct browser version
   - WebDriverManager handles driver downloads automatically

2. **Test Failures**
   - Check screenshots in `test-output/screenshots/`
   - Review logs for detailed error information

3. **Configuration Issues**
   - Verify `config.properties` settings
   - Ensure correct file paths and URLs

## 📝 Best Practices

1. **Page Object Model**: Keep page elements and actions in page classes
2. **Wait Strategies**: Use explicit waits over implicit waits
3. **Test Independence**: Each test should be independent and reusable
4. **Proper Assertions**: Use meaningful assertion messages
5. **Logging**: Add comprehensive logging for debugging
6. **Configuration**: Use external configuration for flexibility

## 🤝 Contributing

1. Follow the existing code structure and patterns
2. Add proper documentation and comments
3. Include appropriate test coverage
4. Update README for new features

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

**Note**: This framework uses SauceDemo (https://www.saucedemo.com/) as the demo website for testing purposes. It's a practice website specifically designed for automation testing.
