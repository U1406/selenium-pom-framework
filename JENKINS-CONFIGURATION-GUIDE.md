# 🚀 Jenkins Configuration Guide - Complete Reference

## 📋 **Table of Contents**
1. [Initial Jenkins Setup](#initial-jenkins-setup)
2. [General Configuration](#general-configuration)
3. [Global Tool Configuration](#global-tool-configuration)
4. [Plugin Management](#plugin-management)
5. [Pipeline Job Creation](#pipeline-job-creation)
6. [Build Parameters Setup](#build-parameters-setup)
7. [GitHub Integration](#github-integration)
8. [Webhook Configuration](#webhook-configuration)
9. [Troubleshooting Guide](#troubleshooting-guide)

---

## 🔧 **1. Initial Jenkins Setup**

### **Step 1.1: Install Jenkins (macOS)**
```bash
# Install Jenkins LTS using Homebrew
brew install jenkins-lts

# Start Jenkins service
brew services start jenkins-lts

# Check if Jenkins is running
brew services list | grep jenkins

# Open Jenkins in browser
open http://localhost:8080
```

### **Step 1.2: Initial Jenkins Configuration**
1. **Unlock Jenkins**: Enter the initial admin password from:
   ```bash
   cat /Users/$(whoami)/.jenkins/secrets/initialAdminPassword
   ```
2. **Install Suggested Plugins**: Choose "Install suggested plugins"
3. **Create Admin User**: Set up your admin credentials
4. **Configure Jenkins URL**: Confirm `http://localhost:8080/`

---

## ⚙️ **2. General Configuration**

### **Step 2.1: System Configuration**
**Navigate**: `Manage Jenkins` → `Configure System`

#### **Jenkins Location**
- **Jenkins URL**: `http://localhost:8080/`
- **System Admin e-mail address**: `your-email@example.com`

#### **Global Properties**
- ☑️ **Environment variables**
- **Add**:
  - **Name**: `JAVA_HOME`
  - **Value**: `/Users/$(whoami)/Library/Java/JavaVirtualMachines/azul-11.0.24/Contents/Home`

#### **Build Record Retention**
- **Strategy**: `Log Rotation`
- **Days to keep builds**: `30`
- **Max # of builds to keep**: `20`

### **Step 2.2: Security Configuration**
**Navigate**: `Manage Jenkins` → `Configure Global Security`

#### **Security Realm**
- **Select**: `Jenkins' own user database`
- ☑️ **Allow users to sign up**

#### **Authorization**
- **Select**: `Logged-in users can do anything`
- ☑️ **Allow anonymous read access** (optional, for public projects)

---

## 🛠️ **3. Global Tool Configuration**

### **Step 3.1: Configure Global Tools**
**Navigate**: `Manage Jenkins` → `Global Tool Configuration`

#### **JDK Configuration**
1. **Click**: `Add JDK`
2. **Configuration**:
   - **Name**: `Java 11` (exactly as used in Jenkinsfile)
   - **JAVA_HOME**: `/Users/$(whoami)/Library/Java/JavaVirtualMachines/azul-11.0.24/Contents/Home`
   - **❌ Uncheck**: "Install automatically" (using system installation)

#### **Maven Configuration**
1. **Click**: `Add Maven`
2. **Configuration**:
   - **Name**: `Maven-3.9.9` (exactly as used in Jenkinsfile)
   - **MAVEN_HOME**: `/opt/homebrew/Cellar/maven/3.9.9/libexec`
   - **❌ Uncheck**: "Install automatically" (using system installation)

#### **Git Configuration**
1. **Git installations**: Usually auto-detected
2. **If not detected**:
   - **Name**: `Default`
   - **Path to Git executable**: `/usr/bin/git`

### **Step 3.2: Verify Tool Paths**
```bash
# Check Java path
echo $JAVA_HOME
/usr/libexec/java_home -v 11

# Check Maven path
which mvn
mvn -version

# Check Git path
which git
git --version
```

---

## 🔌 **4. Plugin Management**

### **Step 4.1: Essential Plugins**
**Navigate**: `Manage Jenkins` → `Manage Plugins` → `Available`

#### **Required Plugins**
- ☑️ **Pipeline**: Core pipeline functionality
- ☑️ **Git**: Git SCM integration
- ☑️ **GitHub**: GitHub integration
- ☑️ **Maven Integration**: Maven build support
- ☑️ **JUnit**: Test result publishing
- ☑️ **HTML Publisher**: HTML report publishing
- ☑️ **Workspace Cleanup**: Clean workspace after builds
- ☑️ **Build Timeout**: Set build timeouts
- ☑️ **Timestamper**: Add timestamps to console output

#### **Optional but Useful Plugins**
- ☑️ **Blue Ocean**: Modern UI for pipelines
- ☑️ **Pipeline Stage View**: Visual pipeline stages
- ☑️ **Email Extension**: Advanced email notifications
- ☑️ **Slack Notification**: Slack integration
- ☑️ **Allure**: Advanced test reporting

### **Step 4.2: Install Plugins**
1. **Select all required plugins**
2. **Click**: "Install without restart"
3. **☑️ Restart Jenkins** when installation is complete

---

## 📦 **5. Pipeline Job Creation**

### **Step 5.1: Create New Pipeline Job**
1. **Click**: `New Item` on Jenkins dashboard
2. **Enter item name**: `Selenium-Automation-Framework`
3. **Select**: `Pipeline`
4. **Click**: `OK`

### **Step 5.2: General Configuration**

#### **Description**
```
Selenium automation framework for web application testing using Page Object Model.
Supports multiple browsers (Chrome, Firefox, Edge) with headless execution.
Integrated with GitHub for automated CI/CD pipeline.
```

#### **Build Settings**
- ☑️ **Discard old builds**
  - **Strategy**: `Log Rotation`
  - **Days to keep builds**: `30`
  - **Max # of builds to keep**: `10`
  - **Max # of builds to keep with artifacts**: `5`

#### **Advanced Project Options**
- **Display Name**: `Selenium Automation Framework`
- ☑️ **Use custom workspace**: `/Users/$(whoami)/.jenkins/workspace/Selenium-Tests`

---

## 🎛️ **6. Build Parameters Setup**

### **Step 6.1: Enable Parameters**
- ☑️ **This project is parameterized**

### **Step 6.2: Add Parameters**

#### **Parameter 1: TEST_TYPE (Choice Parameter)**
- **Name**: `TEST_TYPE`
- **Choices** (one per line):
  ```
  simple
  full
  smoke
  regression
  ```
- **Description**: `Choose which test suite to run`

#### **Parameter 2: BROWSER_TYPE (Choice Parameter)**
- **Name**: `BROWSER_TYPE`
- **Choices** (one per line):
  ```
  chrome
  firefox
  edge
  safari
  ```
- **Description**: `Choose browser for test execution`

#### **Parameter 3: RUN_HEADLESS (Boolean Parameter)**
- **Name**: `RUN_HEADLESS`
- **Default Value**: ☑️ `Checked`
- **Description**: `Run tests in headless mode (no GUI)`

#### **Parameter 4: ENVIRONMENT (Choice Parameter)**
- **Name**: `ENVIRONMENT`
- **Choices** (one per line):
  ```
  dev
  staging
  production
  ```
- **Description**: `Target environment for testing`

#### **Parameter 5: THREAD_COUNT (String Parameter)**
- **Name**: `THREAD_COUNT`
- **Default Value**: `1`
- **Description**: `Number of parallel threads for test execution`

---

## 🔗 **7. GitHub Integration**

### **Step 7.1: Pipeline Configuration**
**In Pipeline section of job configuration:**

#### **Definition**
- **Select**: `Pipeline script from SCM`

#### **SCM Configuration**
- **SCM**: `Git`
- **Repository URL**: `https://github.com/YOUR_USERNAME/selenium-automation-framework.git`
- **Credentials**: `Add` → `Jenkins`

#### **Credentials Setup**
- **Kind**: `Username with password`
- **Username**: Your GitHub username
- **Password**: Your GitHub Personal Access Token (PAT)
- **ID**: `github-pat`
- **Description**: `GitHub Personal Access Token`

#### **Branches to build**
- **Branch Specifier**: `*/main` (not `*/master`)

#### **Additional Behaviors**
- **Clean before checkout**: ☑️
- **Clean after checkout**: ☑️

#### **Script Path**
- **Script Path**: `Jenkinsfile`
- ☑️ **Lightweight checkout**

### **Step 7.2: Create GitHub Personal Access Token**
1. **GitHub** → **Settings** → **Developer settings** → **Personal access tokens** → **Tokens (classic)**
2. **Generate new token** → **Generate new token (classic)**
3. **Configure token**:
   - **Note**: `Jenkins-Selenium-Framework`
   - **Expiration**: `90 days`
   - **Scopes**:
     - ☑️ `repo` (Full control of repositories)
     - ☑️ `workflow` (Update GitHub Action workflows)
     - ☑️ `admin:repo_hook` (Repository hooks)
4. **Generate token** and **copy it** (save securely!)

---

## 🔔 **8. Webhook Configuration**

### **Step 8.1: Build Triggers**
**In job configuration:**
- ☑️ **GitHub hook trigger for GITScm polling**
- ☑️ **Poll SCM**: `H/5 * * * *` (checks every 5 minutes as backup)

### **Step 8.2: GitHub Webhook Setup**
1. **Go to GitHub repository** → **Settings** → **Webhooks**
2. **Add webhook**:
   - **Payload URL**: `http://YOUR_JENKINS_URL:8080/github-webhook/`
   - **Content type**: `application/json`
   - **Which events**: `Just the push event`
   - ☑️ **Active**
3. **Add webhook**

### **Step 8.3: Test Webhook**
```bash
# Make a test commit
echo "# Test webhook integration $(date)" >> README.md
git add README.md
git commit -m "Test automatic build trigger"
git push origin main
```

---

## 📝 **9. Sample Jenkinsfile Configuration**

### **Step 9.1: Complete Jenkinsfile Template**
```groovy
pipeline {
    agent any
    
    tools {
        jdk 'Java 11'
        maven 'Maven-3.9.9'
    }
    
    environment {
        // Browser configuration
        BROWSER = "${params.BROWSER_TYPE}"
        HEADLESS = "${params.RUN_HEADLESS}"
        ENVIRONMENT = "${params.ENVIRONMENT}"
        
        // Test configuration
        TEST_SUITE = 'src/test/resources/testng.xml'
        SIMPLE_TEST_SUITE = 'src/test/resources/simple-testng.xml'
        
        // Reporting
        REPORTS_DIR = 'reports'
        SCREENSHOTS_DIR = 'screenshots'
    }
    
    parameters {
        choice(
            name: 'TEST_TYPE',
            choices: ['simple', 'full', 'smoke', 'regression'],
            description: 'Choose which test suite to run'
        )
        choice(
            name: 'BROWSER_TYPE',
            choices: ['chrome', 'firefox', 'edge'],
            description: 'Choose browser for test execution'
        )
        booleanParam(
            name: 'RUN_HEADLESS',
            defaultValue: true,
            description: 'Run tests in headless mode'
        )
        choice(
            name: 'ENVIRONMENT',
            choices: ['dev', 'staging', 'production'],
            description: 'Target environment for testing'
        )
        string(
            name: 'THREAD_COUNT',
            defaultValue: '1',
            description: 'Number of parallel threads'
        )
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }
        
        stage('Environment Info') {
            steps {
                script {
                    echo "=== BUILD INFORMATION ==="
                    echo "Build Number: ${env.BUILD_NUMBER}"
                    echo "Job Name: ${env.JOB_NAME}"
                    echo "Workspace: ${env.WORKSPACE}"
                    echo "=== TEST CONFIGURATION ==="
                    echo "Test Type: ${params.TEST_TYPE}"
                    echo "Browser: ${params.BROWSER_TYPE}"
                    echo "Headless Mode: ${params.RUN_HEADLESS}"
                    echo "Environment: ${params.ENVIRONMENT}"
                    echo "Thread Count: ${params.THREAD_COUNT}"
                    echo "=== SYSTEM INFORMATION ==="
                    sh 'java -version'
                    sh 'mvn -version'
                    sh 'echo "Node: $(hostname)"'
                    sh 'echo "User: $(whoami)"'
                }
            }
        }
        
        stage('Clean & Compile') {
            parallel {
                stage('Maven Clean') {
                    steps {
                        echo 'Cleaning previous builds...'
                        sh 'mvn clean'
                    }
                }
                stage('Dependency Check') {
                    steps {
                        echo 'Checking dependencies...'
                        sh 'mvn dependency:resolve'
                    }
                }
            }
        }
        
        stage('Compile') {
            steps {
                echo 'Compiling the project...'
                sh 'mvn compile test-compile'
            }
        }
        
        stage('Run Tests') {
            steps {
                script {
                    echo "Running ${params.TEST_TYPE} tests with ${params.BROWSER_TYPE} browser..."
                    
                    def testCommand = """
                        mvn test \
                        -Dsurefire.suiteXmlFiles=src/test/resources/${params.TEST_TYPE == 'simple' ? 'simple-' : ''}testng.xml \
                        -Dbrowser=${params.BROWSER_TYPE} \
                        -Dheadless=${params.RUN_HEADLESS} \
                        -Denvironment=${params.ENVIRONMENT} \
                        -DthreadCount=${params.THREAD_COUNT} \
                        -Dmaven.test.failure.ignore=true
                    """
                    
                    sh testCommand
                }
            }
            post {
                always {
                    // Archive test results
                    echo 'Processing test results...'
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                    
                    // Archive screenshots
                    script {
                        if (fileExists('screenshots')) {
                            archiveArtifacts artifacts: 'screenshots/**/*.png', allowEmptyArchive: true
                        }
                        if (fileExists('test-output')) {
                            archiveArtifacts artifacts: 'test-output/**/*', allowEmptyArchive: true
                        }
                    }
                }
            }
        }
        
        stage('Generate Reports') {
            steps {
                echo 'Generating test reports...'
                script {
                    sh '''
                        mkdir -p reports
                        if [ -d "target/surefire-reports" ]; then
                            cp -r target/surefire-reports/* reports/ 2>/dev/null || true
                        fi
                        if [ -d "test-output" ]; then
                            cp -r test-output/* reports/ 2>/dev/null || true
                        fi
                    '''
                    
                    // Archive reports
                    archiveArtifacts artifacts: 'reports/**/*', allowEmptyArchive: true
                    
                    // Publish HTML reports
                    publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'reports',
                        reportFiles: 'index.html',
                        reportName: 'Test Report'
                    ])
                }
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline execution completed!'
            cleanWs(cleanWhenNotBuilt: false,
                    deleteDirs: true,
                    disableDeferredWipeout: true,
                    notFailBuild: true,
                    patterns: [[pattern: '.gitignore', type: 'INCLUDE'],
                               [pattern: '.propsfile', type: 'EXCLUDE']])
        }
        
        success {
            echo '✅ All tests passed successfully!'
            script {
                def message = """
                🎉 *Build Successful* 🎉
                
                📋 *Build Details:*
                • Job: ${env.JOB_NAME}
                • Build: #${env.BUILD_NUMBER}
                • Test Type: ${params.TEST_TYPE}
                • Browser: ${params.BROWSER_TYPE}
                • Environment: ${params.ENVIRONMENT}
                
                🔗 *Build URL:* ${env.BUILD_URL}
                """
                
                // Send notification (configure as needed)
                echo message
            }
        }
        
        failure {
            echo '❌ Build failed!'
            script {
                // Archive failure artifacts
                if (fileExists('screenshots')) {
                    archiveArtifacts artifacts: 'screenshots/**/*.png', allowEmptyArchive: true
                }
                if (fileExists('logs')) {
                    archiveArtifacts artifacts: 'logs/**/*.log', allowEmptyArchive: true
                }
                
                def message = """
                🚨 *Build Failed* 🚨
                
                📋 *Build Details:*
                • Job: ${env.JOB_NAME}
                • Build: #${env.BUILD_NUMBER}
                • Test Type: ${params.TEST_TYPE}
                • Browser: ${params.BROWSER_TYPE}
                
                🔗 *Build URL:* ${env.BUILD_URL}
                📊 *Console:* ${env.BUILD_URL}console
                """
                
                // Send failure notification
                echo message
            }
        }
        
        unstable {
            echo '⚠️ Build unstable - some tests failed!'
        }
    }
}
```

---

## 🚨 **10. Troubleshooting Guide**

### **Issue 1: Tool Not Found Errors**

#### **Problem**: `mvn: command not found` or `java: command not found`
#### **Solutions**:

**Option A: Configure in Global Tools**
1. `Manage Jenkins` → `Global Tool Configuration`
2. Add tools with correct paths
3. Update Jenkinsfile to reference tool names

**Option B: Use Environment PATH**
```groovy
environment {
    PATH = "/opt/homebrew/bin:/usr/local/bin:${env.PATH}"
}
```

**Option C: Use Full Paths**
```groovy
sh '/opt/homebrew/bin/mvn -version'
```

### **Issue 2: Git Authentication Failed**

#### **Problem**: Cannot access GitHub repository
#### **Solutions**:
1. **Verify PAT permissions**: repo, workflow, admin:repo_hook
2. **Update credentials** in Jenkins
3. **Test repository connection** in job configuration

### **Issue 3: Branch Not Found**

#### **Problem**: `fatal: couldn't find remote ref refs/heads/master`
#### **Solutions**:
1. **Update branch specifier** to `*/main`
2. **Check default branch** in GitHub repository
3. **Verify repository URL** is correct

### **Issue 4: Build Parameters Not Working**

#### **Problem**: Parameters not passed to Maven
#### **Solutions**:
1. **Verify parameter names** match exactly
2. **Use correct syntax**: `${params.PARAMETER_NAME}`
3. **Check parameter types** (choice, boolean, string)

### **Issue 5: Webhook Not Triggering**

#### **Problem**: Automatic builds not starting
#### **Solutions**:
1. **Check webhook URL**: must end with `/github-webhook/`
2. **Verify Jenkins accessibility** from GitHub
3. **Enable polling as backup**: `H/5 * * * *`
4. **Check webhook deliveries** in GitHub

### **Issue 6: Test Results Not Archived**

#### **Problem**: No test results showing in Jenkins
#### **Solutions**:
1. **Verify JUnit XML path**: `**/target/surefire-reports/*.xml`
2. **Check test execution**: ensure tests actually run
3. **Enable `allowEmptyResults: true`** for debugging

---

## 📋 **11. Quick Reference Commands**

### **Jenkins Service Management**
```bash
# Start Jenkins
brew services start jenkins-lts

# Stop Jenkins
brew services stop jenkins-lts

# Restart Jenkins
brew services restart jenkins-lts

# Check Jenkins status
brew services list | grep jenkins
```

### **Common Jenkins URLs**
- **Dashboard**: `http://localhost:8080`
- **Manage Jenkins**: `http://localhost:8080/manage`
- **Global Tools**: `http://localhost:8080/configureTools`
- **Plugin Manager**: `http://localhost:8080/pluginManager`
- **System Configuration**: `http://localhost:8080/configure`

### **Useful Jenkins CLI Commands**
```bash
# Download Jenkins CLI
curl -O http://localhost:8080/jnlpJars/jenkins-cli.jar

# List jobs
java -jar jenkins-cli.jar -s http://localhost:8080/ list-jobs

# Build job
java -jar jenkins-cli.jar -s http://localhost:8080/ build "Job-Name"

# Get job configuration
java -jar jenkins-cli.jar -s http://localhost:8080/ get-job "Job-Name"
```

---

## 🎯 **12. Best Practices**

### **Security**
- ✅ Use Personal Access Tokens instead of passwords
- ✅ Limit PAT permissions to minimum required
- ✅ Regularly rotate credentials
- ✅ Enable CSRF protection
- ✅ Use role-based access control

### **Performance**
- ✅ Clean workspace after builds
- ✅ Limit build history retention
- ✅ Use parallel stages where possible
- ✅ Cache dependencies when possible
- ✅ Use lightweight checkout

### **Maintainability**
- ✅ Use descriptive job and stage names
- ✅ Add comprehensive logging
- ✅ Document parameters and their purposes
- ✅ Use environment variables for configuration
- ✅ Implement proper error handling

### **Testing**
- ✅ Test pipeline changes in separate branch
- ✅ Use Blue Ocean for visual pipeline debugging
- ✅ Monitor build trends and performance
- ✅ Set up proper notifications
- ✅ Archive important artifacts

---

## 📞 **Support & Resources**

### **Official Documentation**
- **Jenkins Official Docs**: https://www.jenkins.io/doc/
- **Pipeline Syntax**: https://www.jenkins.io/doc/book/pipeline/syntax/
- **Plugin Documentation**: https://plugins.jenkins.io/

### **Community Resources**
- **Jenkins Community**: https://community.jenkins.io/
- **Stack Overflow**: Tag `jenkins`
- **GitHub Issues**: https://github.com/jenkinsci/jenkins/issues

---

**Last Updated**: July 13, 2025
**Version**: 1.0
**Author**: GitHub Copilot Assistant

---

> 💡 **Tip**: Bookmark this guide and update it as you customize your Jenkins setup. Each environment may have slight variations, so adapt the configurations according to your specific needs.
