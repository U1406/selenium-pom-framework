# Simple Selenium POM Framework

A minimal, easy-to-understand Selenium framework demonstrating the Page Object Model (POM) pattern.

## 📁 Project Structure (Simplified)

```
simple-selenium-pom/
├── src/
│   ├── main/java/com/selenium/
│   │   ├── pages/
│   │   │   ├── SimpleBasePage.java        # Base page with common methods
│   │   │   ├── SimpleLoginPage.java       # Login page object
│   │   │   └── SimpleDashboardPage.java   # Dashboard page object
│   │   └── utils/
│   │       └── SimpleDriverFactory.java   # WebDriver management
│   └── test/
│       ├── java/com/selenium/tests/
│       │   └── SimpleLoginTest.java       # Login test scenarios
│       └── resources/
│           └── simple-testng.xml          # TestNG configuration
└── simple-pom.xml                         # Maven dependencies
```

## 🎯 Core Components Explained

### 1. **SimpleDriverFactory.java** - WebDriver Management
- Creates and manages Chrome WebDriver
- Handles browser setup and cleanup
- Uses WebDriverManager for automatic driver management

### 2. **SimpleBasePage.java** - Common Page Methods
- Base class for all page objects
- Contains reusable methods: click(), sendKeys(), getText()
- Implements explicit waits for reliable interactions

### 3. **SimpleLoginPage.java** - Login Page Object
- Represents the login page elements and actions
- Uses @FindBy annotations to locate elements
- Provides methods: login(), enterUsername(), enterPassword()

### 4. **SimpleDashboardPage.java** - Dashboard Page Object
- Represents the dashboard after successful login
- Contains methods to verify successful login
- Demonstrates page verification methods

### 5. **SimpleLoginTest.java** - Test Scenarios
- Contains 3 basic test scenarios:
  - Valid login test
  - Invalid login test  
  - Empty credentials test

## 🚀 How to Run

1. **Prerequisites**: Java 11+, Maven 3.6+

2. **Run Tests**:
   ```bash
   mvn test -f simple-pom.xml -Dsurefire.suiteXmlFiles=src/test/resources/simple-testng.xml
   ```

## 🧩 Key POM Concepts Demonstrated

1. **Separation of Concerns**: Page elements and test logic are separated
2. **Reusability**: Page methods can be reused across multiple tests
3. **Maintainability**: Element locators are centralized in page classes
4. **Readability**: Tests read like natural language

## 📝 Why These Files?

- **simple-pom.xml**: Maven configuration with minimal dependencies
- **SimpleDriverFactory.java**: Centralized WebDriver management
- **SimpleBasePage.java**: Common functionality for all pages
- **Page Classes**: Represent web pages as objects with methods
- **Test Classes**: Contain actual test scenarios using page objects
- **simple-testng.xml**: TestNG configuration for running tests

This simplified framework focuses on core POM concepts without overwhelming complexity!
