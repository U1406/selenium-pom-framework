# Jenkins Local Setup Guide

## Prerequisites
- Java 17+ installed
- Maven 3.9+ installed
- Chrome/Firefox browsers installed

## Install Jenkins Locally

### 1. Download Jenkins
```bash
# macOS with Homebrew
brew install jenkins-lts

# Or download WAR file
wget https://get.jenkins.io/war-stable/latest/jenkins.war
```

### 2. Start Jenkins
```bash
# With Homebrew
brew services start jenkins-lts

# Or with WAR file
java -jar jenkins.war --httpPort=8080
```

### 3. Initial Setup
1. Open http://localhost:8080
2. Get initial admin password:
   ```bash
   cat ~/.jenkins/secrets/initialAdminPassword
   ```
3. Install suggested plugins
4. Create admin user

## Configure Tools

### 1. Global Tool Configuration
Navigate to: Manage Jenkins → Global Tool Configuration

#### Maven Configuration
- Name: `Maven-3.9.0`
- Install automatically: ✓
- Version: Latest 3.9.x

#### JDK Configuration  
- Name: `JDK-17`
- Install automatically: ✓
- Version: Latest JDK 17

### 2. Required Plugins
Install these plugins via Manage Jenkins → Manage Plugins:
- Pipeline
- Git
- Maven Integration
- JUnit
- HTML Publisher (for reports)
- Build Timeout (optional)

## Create Pipeline Job

### 1. New Job
- Click "New Item"
- Enter job name: `Selenium-Automation-Framework`
- Select "Pipeline"
- Click OK

### 2. Configuration
#### General
- Description: "Selenium automation tests for SauceDemo"
- ✓ This project is parameterized (optional)

#### Pipeline
- Definition: Pipeline script from SCM
- SCM: Git
- Repository URL: `https://github.com/yourusername/selenium-framework.git`
- Branch: `*/main`
- Script Path: `Jenkinsfile`

### 3. Save and Build
- Click "Save"
- Click "Build with Parameters"
- Select your preferences
- Click "Build"

## Troubleshooting

### Browser Issues
If tests fail with browser errors:
```bash
# Install browsers on Jenkins agent
# Chrome
wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | sudo apt-key add -
sudo sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list'
sudo apt-get update
sudo apt-get install google-chrome-stable

# Firefox
sudo apt-get install firefox
```

### Display Issues (Linux)
For headless execution on Linux:
```bash
# Install Xvfb for virtual display
sudo apt-get install xvfb

# Or use headless mode (already configured)
```

### Permission Issues
```bash
# Fix Jenkins workspace permissions
sudo chown -R jenkins:jenkins /var/lib/jenkins/workspace/
```

## Advanced Configuration

### 1. Email Notifications
Configure SMTP in: Manage Jenkins → Configure System → E-mail Notification

### 2. Slack Integration
Install Slack plugin and configure webhook

### 3. Multi-Node Setup
Add Jenkins agents for parallel execution

### 4. Docker Integration
Run tests in Docker containers for consistency
