#!/bin/bash

echo "============================================"
echo "Simple Selenium POM Framework - Test Runner"
echo "============================================"

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven is not installed. Please install Maven."
    exit 1
fi

echo "✅ Maven found"

# Run the simplified tests
echo "🚀 Running Simple POM Tests..."
mvn test -f simple-pom.xml -Dsurefire.suiteXmlFiles=src/test/resources/simple-testng.xml

if [ $? -eq 0 ]; then
    echo "✅ All tests passed!"
else
    echo "❌ Some tests failed!"
fi

echo "============================================"
