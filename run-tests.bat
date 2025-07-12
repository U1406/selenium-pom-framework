@echo off
REM Selenium POM Framework Test Execution Script for Windows

echo ===========================================
echo Selenium POM Framework Test Execution
echo ===========================================

REM Check if Maven is installed
where mvn >nul 2>nul
if %errorlevel% neq 0 (
    echo [ERROR] Maven is not installed. Please install Maven to continue.
    exit /b 1
)

echo [INFO] Maven found

REM Check if Java is installed
where java >nul 2>nul
if %errorlevel% neq 0 (
    echo [ERROR] Java is not installed. Please install Java 11 or higher.
    exit /b 1
)

echo [INFO] Java found

REM Clean and compile the project
echo [INFO] Cleaning and compiling the project...
call mvn clean compile

if %errorlevel% neq 0 (
    echo [ERROR] Project compilation failed!
    exit /b 1
)

echo [INFO] Project compiled successfully!

REM Create test-output directories
echo [INFO] Creating test output directories...
if not exist "test-output\reports" mkdir "test-output\reports"
if not exist "test-output\screenshots" mkdir "test-output\screenshots"
if not exist "test-output\logs" mkdir "test-output\logs"

REM Default values
set BROWSER=chrome
set HEADLESS=false
set SUITE=src/test/resources/testng.xml

REM Parse command line arguments
:parse_args
if "%1"=="" goto run_tests
if "%1"=="-b" (
    set BROWSER=%2
    shift
    shift
    goto parse_args
)
if "%1"=="--browser" (
    set BROWSER=%2
    shift
    shift
    goto parse_args
)
if "%1"=="-h" (
    set HEADLESS=true
    shift
    goto parse_args
)
if "%1"=="--headless" (
    set HEADLESS=true
    shift
    goto parse_args
)
if "%1"=="-s" (
    set SUITE=%2
    shift
    shift
    goto parse_args
)
if "%1"=="--suite" (
    set SUITE=%2
    shift
    shift
    goto parse_args
)
if "%1"=="--help" (
    echo Usage: %0 [OPTIONS]
    echo Options:
    echo   -b, --browser ^<chrome^|firefox^|edge^>  Browser to use ^(default: chrome^)
    echo   -h, --headless                        Run in headless mode
    echo   -s, --suite ^<path^>                    TestNG suite file path
    echo   --help                                Show this help message
    exit /b 0
)
shift
goto parse_args

:run_tests
echo [INFO] Test Configuration:
echo [INFO]   Browser: %BROWSER%
echo [INFO]   Headless: %HEADLESS%
echo [INFO]   Test Suite: %SUITE%

REM Run the tests
echo [INFO] Starting test execution...
call mvn test -Dbrowser="%BROWSER%" -Dheadless="%HEADLESS%" -Dsurefire.suiteXmlFiles="%SUITE%"

REM Check test execution result
if %errorlevel% equ 0 (
    echo [INFO] All tests executed successfully!
    echo [INFO] Test reports available in: test-output\reports\
    echo [INFO] Screenshots ^(if any^) available in: test-output\screenshots\
    echo [INFO] Logs available in: test-output\logs\
) else (
    echo [ERROR] Some tests failed. Check the reports for details.
)

echo ===========================================
echo Test execution completed!
echo ===========================================
