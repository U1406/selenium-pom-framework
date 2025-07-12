# Contributing to Selenium POM Framework

Thank you for your interest in contributing to the Selenium POM Framework! 🎉

## 🚀 How to Contribute

### 1. Fork the Repository
- Click the "Fork" button on the top right of the repository page
- Clone your forked repository locally

### 2. Set Up Development Environment
```bash
git clone https://github.com/your-username/selenium-pom-framework.git
cd selenium-pom-framework
mvn clean install
```

### 3. Create a Feature Branch
```bash
git checkout -b feature/your-feature-name
```

### 4. Make Your Changes
- Follow the existing code style and conventions
- Add appropriate tests for new features
- Update documentation as needed

### 5. Run Tests
```bash
# Run all tests
mvn test

# Run simple framework tests
mvn test -f simple-pom.xml -Dsurefire.suiteXmlFiles=src/test/resources/simple-testng.xml
```

### 6. Commit Your Changes
```bash
git add .
git commit -m "Add: Description of your changes"
```

### 7. Push and Create Pull Request
```bash
git push origin feature/your-feature-name
```

## 📝 Code Style Guidelines

### Java Code Style
- Use 4 spaces for indentation
- Follow Java naming conventions
- Add JavaDoc comments for public methods
- Keep methods focused and single-purpose

### Test Code Guidelines
- Write descriptive test method names
- Include meaningful assertion messages
- Use Page Object Model pattern
- Add appropriate logging for debugging

### Commit Message Guidelines
- Use present tense ("Add feature" not "Added feature")
- Use imperative mood ("Move cursor to..." not "Moves cursor to...")
- Limit the first line to 72 characters or less
- Reference issues and pull requests liberally

## 🧪 Testing Guidelines

### Adding New Tests
- Follow the existing test structure
- Use appropriate test data
- Include both positive and negative test scenarios
- Ensure tests are independent and can run in any order

### Page Object Guidelines
- Keep page objects focused on a single page
- Use meaningful element names
- Include wait strategies for element interactions
- Add verification methods for page state

## 🐛 Reporting Issues

When reporting issues, please include:
- Clear description of the problem
- Steps to reproduce
- Expected vs actual behavior
- Environment details (OS, browser, Java version)
- Screenshots or logs if applicable

## 🎯 Areas for Contribution

- **New Page Objects**: Add support for additional pages
- **Test Scenarios**: Add more comprehensive test cases
- **Browser Support**: Enhance cross-browser capabilities
- **Reporting**: Improve test reporting features
- **Documentation**: Enhance or translate documentation
- **Performance**: Optimize test execution speed

## 📋 Pull Request Checklist

Before submitting a pull request, ensure:

- [ ] Code follows project style guidelines
- [ ] Tests pass locally
- [ ] New features include appropriate tests
- [ ] Documentation is updated if needed
- [ ] Commit messages are clear and descriptive
- [ ] No merge conflicts exist

## 🤝 Code Review Process

1. **Automated Checks**: CI/CD pipeline runs automatically
2. **Manual Review**: Maintainers review code for quality and standards
3. **Feedback**: Address any review comments
4. **Approval**: Get approval from maintainers
5. **Merge**: Pull request gets merged into main branch

## 📞 Getting Help

If you need help with contributions:
- Create an issue with your question
- Join discussions in existing issues
- Review existing code for patterns and examples

Thank you for contributing to make this framework better! 🚀
