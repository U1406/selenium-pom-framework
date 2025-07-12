# 🚀 Quick Integration Commands Reference

## 📋 **Phase 1: Git Setup (5 minutes)**
```bash
# Navigate to project
cd "/Users/ynoratanaaseri/Documents/JavaCI_CD Integration"

# Initialize Git
git init
git add .
git commit -m "Initial commit: Selenium automation framework"
```

## 📋 **Phase 2: GitHub Setup (10 minutes)**
```bash
# Create repository on GitHub (via website)
# Then connect local to remote:

git remote add origin https://github.com/YOUR_USERNAME/selenium-automation-framework.git
git branch -M main
git push -u origin main

# Configure credentials with PAT
git config --global user.name "YOUR_USERNAME"
git config --global user.email "YOUR_EMAIL"
git config --global credential.helper store
```

## 📋 **Phase 3: Jenkins Setup (15 minutes)**

### Global Tools Configuration:
1. **Manage Jenkins** → **Global Tool Configuration**
2. **Add Maven**: Name = `Maven-3.9.0`, Install automatically
3. **Add JDK**: Name = `JDK-17`, Install automatically

### Create Pipeline Job:
1. **New Item** → **Pipeline** → Name: `Selenium-Automation-Framework`
2. **General**: Add description, discard old builds, parameterized project
3. **Parameters**: 
   - `TEST_TYPE` (Choice): simple, full
   - `BROWSER_TYPE` (Choice): chrome, firefox, edge  
   - `RUN_HEADLESS` (Boolean): default true
4. **Pipeline**: 
   - Definition: Pipeline script from SCM
   - SCM: Git
   - Repository URL: `https://github.com/YOUR_USERNAME/selenium-automation-framework.git`
   - Credentials: Add GitHub PAT
   - Script Path: `Jenkinsfile`

### Test Build:
1. **Build with Parameters**
2. Select: TEST_TYPE=simple, BROWSER_TYPE=chrome, RUN_HEADLESS=true
3. **Click Build**

## 📋 **Phase 4: Webhook Setup (5 minutes)**
1. **GitHub** → **Settings** → **Webhooks** → **Add webhook**
2. **Payload URL**: `http://YOUR_JENKINS_URL:8080/github-webhook/`
3. **Jenkins**: Enable "GitHub hook trigger for GITScm polling"

## ✅ **Test Complete Integration**
```bash
# Make change and push
echo "# Test integration" >> README.md
git add README.md
git commit -m "Test webhook"
git push origin main

# Check Jenkins - should trigger build automatically
```

## 🎯 **Total Time: ~35 minutes**
- Git Setup: 5 minutes
- GitHub Setup: 10 minutes  
- Jenkins Setup: 15 minutes
- Webhook Setup: 5 minutes

## 🔗 **Important URLs**
- **Jenkins**: http://localhost:8080
- **GitHub**: https://github.com/YOUR_USERNAME/selenium-automation-framework
- **Job Console**: http://localhost:8080/job/Selenium-Automation-Framework/
