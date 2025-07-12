#!/bin/bash

# Selenium POM Framework Test Execution Script

echo "==========================================="
echo "Selenium POM Framework Test Execution"
echo "==========================================="

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Function to print colored output
print_status() {
    echo -e "${GREEN}[INFO]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    print_error "Maven is not installed. Please install Maven to continue."
    exit 1
fi

print_status "Maven found: $(mvn -version | head -1)"

# Check if Java is installed
if ! command -v java &> /dev/null; then
    print_error "Java is not installed. Please install Java 11 or higher."
    exit 1
fi

print_status "Java found: $(java -version 2>&1 | head -1)"

# Clean and compile the project
print_status "Cleaning and compiling the project..."
mvn clean compile

if [ $? -ne 0 ]; then
    print_error "Project compilation failed!"
    exit 1
fi

print_status "Project compiled successfully!"

# Create test-output directories
print_status "Creating test output directories..."
mkdir -p test-output/reports
mkdir -p test-output/screenshots
mkdir -p test-output/logs

# Default values
BROWSER="chrome"
HEADLESS="false"
SUITE="src/test/resources/testng.xml"

# Parse command line arguments
while [[ $# -gt 0 ]]; do
    case $1 in
        -b|--browser)
            BROWSER="$2"
            shift 2
            ;;
        -h|--headless)
            HEADLESS="true"
            shift
            ;;
        -s|--suite)
            SUITE="$2"
            shift 2
            ;;
        --help)
            echo "Usage: $0 [OPTIONS]"
            echo "Options:"
            echo "  -b, --browser <chrome|firefox|edge>  Browser to use (default: chrome)"
            echo "  -h, --headless                        Run in headless mode"
            echo "  -s, --suite <path>                    TestNG suite file path"
            echo "  --help                                Show this help message"
            exit 0
            ;;
        *)
            print_warning "Unknown option: $1"
            shift
            ;;
    esac
done

print_status "Test Configuration:"
print_status "  Browser: $BROWSER"
print_status "  Headless: $HEADLESS"
print_status "  Test Suite: $SUITE"

# Run the tests
print_status "Starting test execution..."
mvn test -Dbrowser="$BROWSER" -Dheadless="$HEADLESS" -Dsurefire.suiteXmlFiles="$SUITE"

# Check test execution result
if [ $? -eq 0 ]; then
    print_status "All tests executed successfully!"
    print_status "Test reports available in: test-output/reports/"
    print_status "Screenshots (if any) available in: test-output/screenshots/"
    print_status "Logs available in: test-output/logs/"
else
    print_error "Some tests failed. Check the reports for details."
fi

echo "==========================================="
echo "Test execution completed!"
echo "==========================================="
