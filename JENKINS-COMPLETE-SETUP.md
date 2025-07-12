# 🚀 Complete Jenkins Setup Guide for Selenium Framework

## ✅ Current Status
- ✅ Jenkins is installed and running on http://localhost:8080
- ✅ Initial admin password: `084be12a37274a2f8e1f540ac78791b2`
- ✅ Java 11 is available
- ✅ Maven is available
- ✅ Framework is ready with Jenkinsfile

## 📋 Step-by-Step Setup

### 1. Initial Jenkins Setup
1. **Open Jenkins in browser**: http://localhost:8080
2. **Enter admin password**: `084be12a37274a2f8e1f540ac78791b2`
3. **Install suggested plugins** (click "Install suggested plugins")
4. **Create admin user** (or skip and continue as admin)
5. **Instance configuration** (keep default: http://localhost:8080)

### 2. Configure Global Tools
Navigate to: **Manage Jenkins** → **Global Tool Configuration**

#### Maven Configuration
- Click **Add Maven**
- Name: `Maven-3.9.0`
- ✅ Install automatically
- Version: Latest 3.9.x (or use existing: `/opt/homebrew/bin/mvn`)

#### JDK Configuration
- Click **Add JDK**
- Name: `JDK-17` 
- ✅ Install automatically
- Version: Latest JDK 17 (or use existing Java 11: `/usr/bin/java`)

### 3. Install Required Plugins
Navigate to: **Manage Jenkins** → **Manage Plugins** → **Available**

Install these plugins:
- ✅ Pipeline (already installed)
- ✅ Git (already installed)
- ✅ Maven Integration
- ✅ JUnit
- ✅ HTML Publisher (for test reports)
- ✅ Build Timeout (optional)

### 4. Create Pipeline Job

#### Step 4.1: Create New Job
1. Click **"New Item"** on Jenkins dashboard
2. Enter job name: `Selenium-Automation-Framework`
3. Select **"Pipeline"**
4. Click **"OK"**

#### Step 4.2: Configure Pipeline
1. **Description**: `Selenium automation tests for SauceDemo using POM framework`
2. **✅ This project is parameterized** (check this box)
3. Add parameters (these are already defined in Jenkinsfile):
   - **Choice Parameter**: 
     - Name: `TEST_TYPE`
     - Choices: `full` and `simple`
   - **Choice Parameter**: 
     - Name: `BROWSER_TYPE` 
     - Choices: `chrome`, `firefox`, `edge`
   - **Boolean Parameter**: 
     - Name: `RUN_HEADLESS`
     - Default: `true`

#### Step 4.3: Pipeline Definition
1. **Definition**: Pipeline script from SCM
2. **SCM**: Git
3. **Repository URL**: 
   - For local testing: `file:///Users/ynoratanaaseri/Documents/JavaCI_CD Integration`
   - For GitHub: `https://github.com/yourusername/selenium-framework.git`
4. **Branch**: `*/main`
5. **Script Path**: `Jenkinsfile`

### 5. Save and Test Build

1. Click **"Save"**
2. Click **"Build with Parameters"**
3. Select your preferences:
   - **TEST_TYPE**: `simple` (for quick test)
   - **BROWSER_TYPE**: `chrome`
   - **RUN_HEADLESS**: `true`
4. Click **"Build"**

## 🎯 Quick Test Commands

### Test Framework Locally First
```bash
cd "/Users/ynoratanaaseri/Documents/JavaCI_CD Integration"

# Run simple tests
./run-simple-tests.sh

# Run full tests
./run-tests.sh
```

### Test Maven Commands
```bash
# Clean and compile
mvn clean compile

# Run simple tests
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/simple-testng.xml

# Run full tests  
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml
```

## 📊 Expected Results

### When Build Succeeds You'll See:
- ✅ Console output showing test execution
- ✅ Test results in Jenkins dashboard
- ✅ Generated reports in `target/` directory
- ✅ Screenshots for failed tests (if any)

### Build Artifacts:
- `target/surefire-reports/` - TestNG HTML reports
- `target/screenshots/` - Failure screenshots
- `target/logs/` - Application logs

## 🔧 Troubleshooting

### If Build Fails:

#### 1. Java/Maven Issues
```bash
# Verify Java
java -version
which java

# Verify Maven
mvn -version
which mvn
```

#### 2. Browser Issues
```bash
# Install Chrome (if needed)
brew install --cask google-chrome

# Install Firefox (if needed)
brew install --cask firefox
```

#### 3. Permission Issues
```bash
# Fix script permissions
chmod +x run-simple-tests.sh
chmod +x run-tests.sh
```

#### 4. Dependencies Issues
```bash
# Update Maven dependencies
mvn clean install -U
```

## 🚀 Advanced: GitHub Integration

### If you want to connect to GitHub:
1. Push your repository to GitHub first:
   ```bash
   cd "/Users/ynoratanaaseri/Documents/JavaCI_CD Integration"
   git remote add origin https://github.com/yourusername/selenium-framework.git
   git push -u origin main
   ```

2. Update Pipeline configuration:
   - **Repository URL**: `https://github.com/yourusername/selenium-framework.git`
   - **Credentials**: Add GitHub credentials if private repo

## 📈 Next Steps

1. **✅ Complete Jenkins setup** (follow steps 1-5 above)
2. **✅ Run your first build**
3. **📊 Review test reports**
4. **🔄 Set up automatic builds** (GitHub webhooks)
5. **📧 Configure email notifications** (optional)

## 🎯 Test Scenarios Available

Your framework includes these test scenarios:
- **Simple Login Test**: Basic login/logout functionality
- **Data-Driven Tests**: Multiple user credentials
- **Page Object Model**: Maintainable test structure
- **Cross-browser Testing**: Chrome, Firefox, Edge
- **Headless Execution**: For CI/CD environments

---

## 🔗 Quick Links
- **Jenkins Dashboard**: http://localhost:8080
- **Build Console**: http://localhost:8080/job/Selenium-Automation-Framework/
- **Test Reports**: Available after build completion

**Ready to start? Follow the steps above and run your first automated test! 🚀**

## 🎯 Jenkins Job Configuration - General Section

### After Creating Your Pipeline Job: General Settings

When you created `Selenium-Automation-Framework` job, here are the **General** section options to configure:

#### ✅ **Description** (Recommended)
```
Selenium automation framework for SauceDemo website using Page Object Model.
Supports multiple browsers (Chrome, Firefox, Edge) and test suites (Simple/Full).
```

#### ✅ **Discard old builds** (Highly Recommended)
- **☑️ Check this option**
- **Strategy**: Log Rotation
- **Max # of builds to keep**: `10` (or your preference)
- **Days to keep builds**: `30` (or your preference)
- **Max # of builds to keep with artifacts**: `5`

*Why: Prevents disk space issues from accumulating test reports and screenshots*

#### ✅ **This project is parameterized** (REQUIRED)
- **☑️ Check this option** 
- Add these parameters (they should match your Jenkinsfile):

##### Parameter 1: TEST_TYPE
- **Type**: Choice Parameter
- **Name**: `TEST_TYPE`
- **Choices**: 
  ```
  simple
  full
  ```
- **Description**: `Choose which test suite to run`

##### Parameter 2: BROWSER_TYPE  
- **Type**: Choice Parameter
- **Name**: `BROWSER_TYPE`
- **Choices**:
  ```
  chrome
  firefox
  edge
  ```
- **Description**: `Choose browser for test execution`

##### Parameter 3: RUN_HEADLESS
- **Type**: Boolean Parameter
- **Name**: `RUN_HEADLESS`
- **Default Value**: ☑️ **Checked (true)**
- **Description**: `Run tests in headless mode`

#### ⚠️ **Throttle builds** (Optional)
- **☐ Leave unchecked** (unless you have resource constraints)

#### ⚠️ **Disable this project** (Optional)
- **☐ Leave unchecked** (you want to run tests)

#### ⚠️ **Use custom workspace** (Optional)
- **☐ Leave unchecked** (use default workspace)

#### ⚠️ **Restrict where this project can be run** (Optional)
- **☐ Leave unchecked** (unless you have specific node requirements)

### 📸 Visual Guide: Jenkins General Configuration

#### Step-by-Step Screenshots Guide:

1. **After creating the job, you'll see the configuration page**
2. **General section should look like this:**

```
┌─ General ────────────────────────────────────────────┐
│                                                      │
│ Description: [Selenium automation framework...]     │
│                                                      │
│ ☑️ Discard old builds                                │
│   Strategy: Log Rotation                            │
│   Max # of builds to keep: 10                       │
│   Days to keep builds: 30                           │
│   Max # of builds to keep with artifacts: 5         │
│                                                      │
│ ☑️ This project is parameterized                     │
│   📊 Choice Parameter: TEST_TYPE                     │
│   📊 Choice Parameter: BROWSER_TYPE                  │
│   📊 Boolean Parameter: RUN_HEADLESS                 │
│                                                      │
│ ☐ Throttle builds                                   │
│ ☐ Disable this project                              │
│ ☐ Use custom workspace                              │
│ ☐ Restrict where this project can be run            │
│                                                      │
└──────────────────────────────────────────────────────┘
```

#### 🔧 Adding Parameters:

**For each parameter, click "Add Parameter" and select:**

**Parameter 1:**
```
Type: Choice Parameter
Name: TEST_TYPE
Choices: (one per line)
  simple
  full
Description: Choose which test suite to run
```

**Parameter 2:**
```
Type: Choice Parameter  
Name: BROWSER_TYPE
Choices: (one per line)
  chrome
  firefox
  edge
Description: Choose browser for test execution
```

**Parameter 3:**
```
Type: Boolean Parameter
Name: RUN_HEADLESS
Default Value: ☑️ Checked
Description: Run tests in headless mode
```

---

### 🎯 Recommended General Configuration:

```
✅ Description: "Selenium automation framework..."
✅ Discard old builds: 10 builds, 30 days
✅ This project is parameterized: 3 parameters added
☐ Throttle builds: Unchecked
☐ Disable this project: Unchecked  
☐ Use custom workspace: Unchecked
☐ Restrict where project can run: Unchecked
```

### 🔄 After General Section: What's Next?

Once you've configured the **General** section, scroll down to configure these sections:

#### 📋 **Build Triggers** (Optional)
Common options:
- **☐ Build after other projects are built**: Leave unchecked
- **☐ Build periodically**: Leave unchecked (unless you want scheduled builds)
- **☐ GitHub hook trigger**: Leave unchecked (for now)
- **☐ Poll SCM**: Leave unchecked (unless you want periodic Git checking)

*Recommendation: Leave all unchecked for manual testing initially*

#### 🔧 **Advanced Project Options** (Optional)
- **☐ Quiet period**: Leave default (5 seconds)
- **☐ Retry Count**: Leave default (0)
- **☐ Block build when upstream/downstream are building**: Leave unchecked

#### 📦 **Pipeline** (MOST IMPORTANT)
This is where you configure your Jenkinsfile:

```
Definition: Pipeline script from SCM
SCM: Git
Repository URL: file:///Users/ynoratanaaseri/Documents/JavaCI_CD Integration
Branch Specifier: */main
Script Path: Jenkinsfile
Lightweight checkout: ☑️ Checked
```

**OR if using GitHub:**
```
Repository URL: https://github.com/yourusername/selenium-framework.git
Credentials: (add GitHub credentials if needed)
```

#### 💾 **Save Configuration**
1. Click **"Save"** button at the bottom
2. You'll be redirected to the job dashboard

---

## 🎯 **EXACT Jenkins Configuration Values**

### **General Section:**
```
☑️ This project is parameterized
☑️ Discard old builds
☐ Do not allow concurrent builds
☐ Do not allow the pipeline to resume if the controller restarts  
☐ GitHub project
☐ Pipeline speed/durability override
☐ Preserve stashes from completed builds
☐ Throttle builds
```

### **Parameters to Add (after checking "This project is parameterized"):**

#### Parameter 1:
```
Type: Choice Parameter
Name: TEST_TYPE
Choices: 
simple
full
Description: Choose which test suite to run
```

#### Parameter 2:
```
Type: Choice Parameter
Name: BROWSER_TYPE
Choices:
chrome
firefox
edge
Description: Choose browser for test execution
```

#### Parameter 3:
```
Type: Boolean Parameter
Name: RUN_HEADLESS
Default Value: ☑️ Checked
Description: Run tests in headless mode
```

### **Discard Old Builds Configuration:**
```
Strategy: Log Rotation
Max # of builds to keep: 10
Days to keep builds: 30
Max # of builds to keep with artifacts: 5
```

### **Triggers Section:**
```
☐ Build after other projects are built
☐ Build periodically  
☐ GitHub hook trigger for GITScm polling
☐ Poll SCM
☐ Trigger builds remotely
```

### **Pipeline Section:**
```
Definition: Pipeline script from SCM
SCM: Git
Repository URL: file:///Users/ynoratanaaseri/Documents/JavaCI_CD Integration
Branches to build: */main
Repository browser: (Auto)
Additional Behaviours: (none)
Script Path: Jenkinsfile
Lightweight checkout: ☑️ Checked
```

### 🚨 **IMPORTANT: Repository URL Fix**

**❌ WRONG:**
```
file:///Users/ynoratanaaseri/Documents/JavaCI_CD
```

**✅ CORRECT:**
```
file:///Users/ynoratanaaseri/Documents/JavaCI_CD Integration
```

**Error Fix**: The space in "JavaCI_CD Integration" folder name requires the complete path!

---

### 🎯 **Exact "Discard Old Builds" Configuration**

**For your Selenium automation framework, enter these exact values:**

```
┌─ Discard old builds ─────────────────────────────────┐
│ ☑️ Strategy: Log Rotation                            │
│                                                      │
│ Days to keep builds: 30                             │
│ (builds older than 30 days will be deleted)         │
│                                                      │
│ Max # of builds to keep: 10                         │
│ (only keep the last 10 builds)                      │
│                                                      │
│ 📂 Advanced (click to expand):                       │
│ Max # of builds to keep with artifacts: 5           │
│ (test reports & screenshots for last 5 builds only) │
│                                                      │
└──────────────────────────────────────────────────────┘
```

**Step-by-step:**
1. **Strategy**: Keep as `Log Rotation`
2. **Days to keep builds**: Type `30`
3. **Max # of builds to keep**: Type `10`
4. **Click "Advanced"** and set:
   - **Max # of builds to keep with artifacts**: Type `5`

### 💾 **Why These Values Work Well:**

- **30 days**: Good for reviewing test trends and debugging
- **10 builds**: Sufficient history without using too much disk space
- **5 artifacts**: Test reports and screenshots can be large files
  - Your framework generates:
    - TestNG HTML reports
    - Screenshots for failed tests
    - Surefire XML reports
    - Console logs

### 🚨 **What Happens:**
- After 30 days: Old builds automatically deleted
- After 10 builds: Oldest builds deleted (even if < 30 days)
- After 5 builds: Test reports/screenshots deleted (but build history remains)

### 🔄 **Alternative Settings (if you prefer):**

**For More Storage:**
```
Days to keep builds: 60
Max # of builds to keep: 20
Max # of builds to keep with artifacts: 10
```

**For Less Storage:**
```
Days to keep builds: 14
Max # of builds to keep: 5
Max # of builds to keep with artifacts: 3
```

---

## 📋 **Step-by-Step Parameter Configuration**

#### **Parameter 1: TEST_TYPE (Choice Parameter)**
```
┌─ Add Parameter: Choice Parameter ────────────────────┐
│                                                      │
│ Name: TEST_TYPE                                      │
│                                                      │
│ Choices: (one choice per line)                       │
│ ┌──────────────────────────────────────────────────┐ │
│ │ simple                                           │ │
│ │ full                                             │ │
│ └──────────────────────────────────────────────────┘ │
│                                                      │
│ Description: Choose which test suite to run         │
│                                                      │
└──────────────────────────────────────────────────────┘
```

#### **Parameter 2: BROWSER_TYPE (Choice Parameter)**  
```
┌─ Add Parameter: Choice Parameter ────────────────────┐
│                                                      │
│ Name: BROWSER_TYPE                                   │
│                                                      │
│ Choices: (one choice per line)                       │
│ ┌──────────────────────────────────────────────────┐ │
│ │ chrome                                           │ │
│ │ firefox                                          │ │
│ │ edge                                             │ │
│ └──────────────────────────────────────────────────┘ │
│                                                      │
│ Description: Choose browser for test execution      │
│                                                      │
└──────────────────────────────────────────────────────┘
```

#### **Parameter 3: RUN_HEADLESS (Boolean Parameter)**
```
┌─ Add Parameter: Boolean Parameter ───────────────────┐
│                                                      │
│ Name: RUN_HEADLESS                                   │
│                                                      │
│ Default Value: ☑️ Set by default                     │
│                                                      │
│ Description: Run tests in headless mode             │
│                                                      │
└──────────────────────────────────────────────────────┘
```

### 🎯 **What Each Parameter Does:**

1. **TEST_TYPE**: 
   - `simple` = Runs basic login test (faster)
   - `full` = Runs all tests including data-driven tests

2. **BROWSER_TYPE**: 
   - `chrome` = Uses Chrome browser
   - `firefox` = Uses Firefox browser  
   - `edge` = Uses Microsoft Edge browser

3. **RUN_HEADLESS**:
   - `true` ✅ = Runs browser without GUI (faster, good for CI/CD)
   - `false` ☐ = Shows browser window (good for debugging)

### 📸 **Visual Guide - Parameter Dropdown Options:**

When you click **"Add Parameter"**, you'll see these options:
```
┌─ Add Parameter Dropdown ─────────────────────────────┐
│ Boolean Parameter         ← Use for RUN_HEADLESS    │
│ Choice Parameter          ← Use for TEST_TYPE       │
│ Choice Parameter          ← Use for BROWSER_TYPE    │ 
│ Credentials Parameter                                │
│ File Parameter                                       │
│ Multi-line String Parameter                          │
│ Password Parameter                                    │
│ Run Parameter                                        │
│ String Parameter                                     │
│ Text Parameter                                       │
└──────────────────────────────────────────────────────┘
```

**You only need:**
- **Choice Parameter** (for TEST_TYPE and BROWSER_TYPE)
- **Boolean Parameter** (for RUN_HEADLESS)

---
