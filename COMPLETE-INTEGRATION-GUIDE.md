# 🚀 Complete Integration Guide: Git → GitHub → Jenkins

## 🎯 Overview
This guide covers the complete process of integrating your Selenium automation framework:
1. **Git Setup** (Local repository)
2. **GitHub Integration** (Remote repository)
3. **Jenkins Integration** (CI/CD Pipeline)

---

## 📋 **Phase 1: Git Repository Setup (Local)**

### Step 1: Initialize Git Repository
```bash
# Navigate to your project directory
cd "/Users/ynoratanaaseri/Documents/JavaCI_CD Integration"

# Initialize Git repository
git init

# Check status
git status
```

### Step 2: Create .gitignore File
```bash
# Create .gitignore to exclude unnecessary files
cat > .gitignore << 'EOF'
# Compiled class files
*.class

# Log files
*.log

# Maven
target/
pom.xml.tag
pom.xml.releaseBackup
pom.xml.versionsBackup

# IDE Files
.idea/
*.iws
*.iml
*.ipr
.vscode/

# OS Files
.DS_Store
Thumbs.db

# Test Output
test-output/
screenshots/
reports/
allure-results/

# Selenium drivers
chromedriver
geckodriver
msedgedriver

# Environment files
.env
*.tmp
*.temp
EOF
```

### Step 3: Add Files to Git
```bash
# Add all files to staging area
git add .

# Check what will be committed
git status

# Make initial commit
git commit -m "Initial commit: Selenium automation framework with POM and Jenkins integration"
```

### Step 4: Verify Git Setup
```bash
# Check commit history
git log --oneline

# Check current branch
git branch

# Check repository status
git status
```

---

## 📋 **Phase 2: GitHub Integration (Remote)**

### Step 1: Create GitHub Repository

#### Option A: Using GitHub Website
1. **Go to GitHub**: https://github.com
2. **Sign in** to your account
3. **Click "+" icon** → "New repository"
4. **Repository details**:
   - **Name**: `selenium-automation-framework`
   - **Description**: `Selenium automation framework using Page Object Model with Jenkins CI/CD integration`
   - **Visibility**: Public (or Private if preferred)
   - **❌ Don't initialize** with README, .gitignore, or license (we already have them)
5. **Click "Create repository"**

#### Option B: Using GitHub CLI (if installed)
```bash
# Install GitHub CLI (if not installed)
brew install gh

# Authenticate with GitHub
gh auth login

# Create repository
gh repo create selenium-automation-framework --public --description "Selenium automation framework using Page Object Model with Jenkins CI/CD integration"
```

### Step 2: Connect Local Repository to GitHub
```bash
# Add GitHub repository as remote origin
git remote add origin https://github.com/YOUR_USERNAME/selenium-automation-framework.git

# Verify remote connection
git remote -v

# Rename default branch to main (if needed)
git branch -M main
```

### Step 3: Push Code to GitHub
```bash
# Push code to GitHub for the first time
git push -u origin main

# Verify push was successful
git log --oneline
```

### Step 4: Set Up GitHub Personal Access Token (PAT)

#### Step 4.1: Create PAT
1. **Go to GitHub** → **Settings** → **Developer settings** → **Personal access tokens** → **Tokens (classic)**
2. **Click "Generate new token"** → **"Generate new token (classic)"**
3. **Configure token**:
   - **Note**: `Jenkins-Selenium-Framework`
   - **Expiration**: `90 days` (or as needed)
   - **Scopes**: Check these boxes:
     - ☑️ `repo` (Full control of private repositories)
     - ☑️ `workflow` (Update GitHub Action workflows)
     - ☑️ `admin:repo_hook` (Repository hooks)
4. **Click "Generate token"**
5. **Copy the token** (save it securely - you won't see it again!)

#### Step 4.2: Configure Git Credentials
```bash
# Configure Git with your GitHub username and email
git config --global user.name "YOUR_GITHUB_USERNAME"
git config --global user.email "YOUR_EMAIL@example.com"

# Store credentials (use PAT as password)
git config --global credential.helper store

# Test connection by making a small change
echo "# Selenium Automation Framework" >> README.md
git add README.md
git commit -m "Update README"
git push origin main
# Enter your GitHub username and PAT when prompted
```

### Step 5: Verify GitHub Integration
```bash
# Check remote repository status
git remote show origin

# Pull latest changes (should be up to date)
git pull origin main

# Check GitHub repository in browser
open https://github.com/YOUR_USERNAME/selenium-automation-framework
```

---

## 📋 **Phase 3: Jenkins Integration (CI/CD)**

### Step 1: Ensure Jenkins is Running
```bash
# Check if Jenkins is running
brew services list | grep jenkins

# Start Jenkins if not running
brew services start jenkins-lts

# Open Jenkins in browser
open http://localhost:8080
```

### Step 2: Configure Jenkins Global Tools

#### Step 2.1: Configure Maven
1. **Go to**: Jenkins Dashboard → **Manage Jenkins** → **Global Tool Configuration**
2. **Maven section**:
   - **Click "Add Maven"**
   - **Name**: `Maven-3.9.0`
   - **☑️ Install automatically**
   - **Version**: Latest 3.9.x
3. **Click "Save"**

#### Step 2.2: Configure JDK
1. **In same page** → **JDK section**:
   - **Click "Add JDK"**
   - **Name**: `JDK-17`
   - **☑️ Install automatically**
   - **Version**: Latest JDK 17
2. **Click "Save"**

### Step 3: Install Required Jenkins Plugins
1. **Go to**: **Manage Jenkins** → **Manage Plugins** → **Available**
2. **Search and install**:
   - ☑️ **Pipeline**
   - ☑️ **Git**
   - ☑️ **Maven Integration**
   - ☑️ **JUnit**
   - ☑️ **HTML Publisher**
   - ☑️ **GitHub Integration**
3. **Click "Install without restart"**
4. **Restart Jenkins** when installation completes

### Step 4: Create Jenkins Pipeline Job

#### Step 4.1: Create New Job
1. **Click "New Item"** on Jenkins dashboard
2. **Job configuration**:
   - **Name**: `Selenium-Automation-Framework`
   - **Type**: Select **"Pipeline"**
3. **Click "OK"**

#### Step 4.2: Configure General Settings
1. **Description**:
   ```
   Selenium automation framework for SauceDemo using Page Object Model.
   Supports Chrome, Firefox, Edge browsers with headless execution.
   Integrated with GitHub for automated CI/CD.
   ```

2. **☑️ Discard old builds**:
   - **Strategy**: Log Rotation
   - **Days to keep builds**: `30`
   - **Max # of builds to keep**: `10`
   - **Advanced** → **Max # of builds to keep with artifacts**: `5`

3. **☑️ This project is parameterized**:
   
   **Parameter 1** - Click "Add Parameter" → "Choice Parameter":
   ```
   Name: TEST_TYPE
   Choices: (one per line)
   simple
   full
   Description: Choose which test suite to run
   ```
   
   **Parameter 2** - Click "Add Parameter" → "Choice Parameter":
   ```
   Name: BROWSER_TYPE
   Choices: (one per line)
   chrome
   firefox
   edge
   Description: Choose browser for test execution
   ```
   
   **Parameter 3** - Click "Add Parameter" → "Boolean Parameter":
   ```
   Name: RUN_HEADLESS
   Default Value: ☑️ Checked
   Description: Run tests in headless mode
   ```

#### Step 4.3: Configure Build Triggers (Optional)
For automated builds on code changes:
- **☑️ GitHub hook trigger for GITScm polling**
- **☑️ Poll SCM**: `H/5 * * * *` (checks every 5 minutes)

#### Step 4.4: Configure Pipeline
1. **Definition**: `Pipeline script from SCM`
2. **SCM**: `Git`
3. **Repository URL**: 
   ```
   https://github.com/YOUR_USERNAME/selenium-automation-framework.git
   ```
4. **Credentials**: 
   - **Click "Add" → "Jenkins"**
   - **Kind**: Username with password
   - **Username**: Your GitHub username
   - **Password**: Your GitHub PAT (Personal Access Token)
   - **ID**: `github-pat`
   - **Description**: `GitHub Personal Access Token`
   - **Click "Add"**
   - **Select the credential** you just created
5. **Branches to build**: `*/main`
6. **Script Path**: `Jenkinsfile`
7. **☑️ Lightweight checkout**

#### Step 4.5: Save Configuration
1. **Click "Save"**
2. You'll be redirected to the job dashboard

### Step 5: Test Jenkins Pipeline

#### Step 5.1: Manual Build Test
1. **Click "Build with Parameters"**
2. **Select parameters**:
   - **TEST_TYPE**: `simple`
   - **BROWSER_TYPE**: `chrome`
   - **RUN_HEADLESS**: ☑️ `true`
3. **Click "Build"**

#### Step 5.2: Monitor Build Progress
1. **Click on build number** (e.g., "#1") in Build History
2. **Click "Console Output"** to see real-time logs
3. **Wait for build completion**

#### Step 5.3: Review Build Results
**If successful, you should see**:
- ✅ Green build status
- ✅ Test results in dashboard
- ✅ Generated artifacts (reports, screenshots)

### Step 6: Set Up GitHub Webhooks (Automated Builds)

#### Step 6.1: Configure GitHub Webhook
1. **Go to your GitHub repository**
2. **Settings** → **Webhooks** → **Add webhook**
3. **Webhook configuration**:
   - **Payload URL**: `http://YOUR_JENKINS_URL:8080/github-webhook/`
   - **Content type**: `application/json`
   - **Which events**: `Just the push event`
   - **☑️ Active**
4. **Click "Add webhook"**

#### Step 6.2: Update Jenkins Job for Webhook
1. **Go to Jenkins job configuration**
2. **Build Triggers section**:
   - **☑️ GitHub hook trigger for GITScm polling**
3. **Save configuration**

### Step 7: Test Complete Integration

#### Step 7.1: Make Code Change
```bash
# Make a small change to trigger build
echo "# Updated $(date)" >> README.md
git add README.md
git commit -m "Test webhook integration"
git push origin main
```

#### Step 7.2: Verify Automatic Build
1. **Check Jenkins dashboard** - should see new build triggered automatically
2. **Monitor build progress**
3. **Verify build completion**

---

## 🎯 **Phase 4: Advanced Configuration**

### Step 1: Configure Build Notifications

#### Email Notifications
1. **Manage Jenkins** → **Configure System** → **E-mail Notification**
2. **Configure SMTP settings**
3. **Add to Jenkinsfile** post-build actions:
```groovy
post {
    success {
        emailext (
            subject: "✅ Build Success: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
            body: "Build completed successfully!",
            to: "your-email@example.com"
        )
    }
    failure {
        emailext (
            subject: "❌ Build Failed: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
            body: "Build failed. Please check the console output.",
            to: "your-email@example.com"
        )
    }
}
```

### Step 2: Configure Multi-Branch Pipeline (Optional)
For managing multiple branches:
1. **New Item** → **Multibranch Pipeline**
2. **Configure branch sources**
3. **Set up branch discovery strategies**

### Step 3: Configure Parallel Execution (Optional)
Update Jenkinsfile for parallel browser testing:
```groovy
stage('Parallel Tests') {
    parallel {
        stage('Chrome Tests') {
            steps {
                sh "mvn test -Dbrowser=chrome"
            }
        }
        stage('Firefox Tests') {
            steps {
                sh "mvn test -Dbrowser=firefox"
            }
        }
    }
}
```

---

## ✅ **Verification Checklist**

### Git Integration ✅
- [ ] Repository initialized with `git init`
- [ ] `.gitignore` configured properly
- [ ] Initial commit made
- [ ] Local repository working

### GitHub Integration ✅
- [ ] GitHub repository created
- [ ] Local repository connected to GitHub
- [ ] Code pushed to GitHub successfully
- [ ] Personal Access Token configured
- [ ] GitHub credentials working

### Jenkins Integration ✅
- [ ] Jenkins running on `http://localhost:8080`
- [ ] Global tools configured (Maven, JDK)
- [ ] Required plugins installed
- [ ] Pipeline job created and configured
- [ ] Parameters set up correctly
- [ ] GitHub repository connected to Jenkins
- [ ] Manual build tested successfully
- [ ] Webhook configured for automatic builds
- [ ] Automatic build triggered by code push

### Complete Integration ✅
- [ ] Code changes trigger automatic Jenkins builds
- [ ] Build results visible in Jenkins dashboard
- [ ] Test reports generated and archived
- [ ] Failed tests captured with screenshots
- [ ] Build notifications working (if configured)

---

## 🔧 **Troubleshooting Common Issues**

### Issue 1: Git Authentication Failed
```bash
# Clear stored credentials
git config --global --unset credential.helper

# Use GitHub PAT instead of password
git config --global credential.helper store
# When prompted, use GitHub username and PAT
```

### Issue 2: Jenkins Cannot Access GitHub
1. **Check GitHub credentials** in Jenkins
2. **Verify PAT permissions** (repo, workflow scopes)
3. **Test repository connection** in Jenkins job configuration

### Issue 3: Build Fails with Maven Issues
```bash
# Update Maven dependencies
mvn clean install -U

# Check Maven configuration in Jenkins Global Tool Configuration
```

### Issue 4: Browser Issues in Jenkins
```bash
# For headless execution issues, ensure Jenkinsfile has:
environment {
    HEADLESS = 'true'
}
```

### Issue 5: Webhook Not Triggering Builds
1. **Check webhook configuration** in GitHub repository settings
2. **Verify Jenkins URL** is accessible from GitHub
3. **Check webhook delivery** in GitHub settings

---

## 🎉 **Success! Your Integration is Complete**

You now have a fully integrated CI/CD pipeline:

**🔄 Workflow**:
1. **Developer** makes code changes
2. **Git** tracks changes locally
3. **GitHub** receives pushed changes
4. **Webhook** triggers Jenkins build automatically
5. **Jenkins** runs Selenium tests
6. **Results** are reported back with artifacts

**🚀 Next Steps**:
- Add more test scenarios
- Configure additional browsers
- Set up staging/production deployments
- Add code quality checks (SonarQube)
- Configure test reporting (Allure)

**🔗 Quick Links**:
- **Jenkins Dashboard**: http://localhost:8080
- **GitHub Repository**: https://github.com/YOUR_USERNAME/selenium-automation-framework
- **Build Console**: http://localhost:8080/job/Selenium-Automation-Framework/

Your Selenium automation framework is now fully integrated with GitHub and Jenkins! 🎯
