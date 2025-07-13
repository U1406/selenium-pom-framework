pipeline {
    agent any
    
    tools {
        jdk 'Java 11' // Using the Java 11 installation that Jenkins detected
    }
    
    environment {
        // Add Maven to PATH
        PATH = "/opt/homebrew/bin:${env.PATH}"
        
        // Browser configuration
        BROWSER = 'chrome'
        HEADLESS = 'true'
        
        // Test configuration
        TEST_SUITE = 'src/test/resources/testng.xml'
        SIMPLE_TEST_SUITE = 'src/test/resources/simple-testng.xml'
        
        // Reporting
        ALLURE_RESULTS_DIR = 'target/allure-results'
        REPORTS_DIR = 'reports'
    }
    
    parameters {
        choice(
            name: 'TEST_TYPE',
            choices: ['full', 'simple'],
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
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }
        
        stage('Build Info') {
            steps {
                script {
                    echo "Build Number: ${env.BUILD_NUMBER}"
                    echo "Build ID: ${env.BUILD_ID}"
                    echo "Job Name: ${env.JOB_NAME}"
                    echo "Workspace: ${env.WORKSPACE}"
                    echo "Test Type: ${params.TEST_TYPE}"
                    echo "Browser: ${params.BROWSER_TYPE}"
                    echo "Headless: ${params.RUN_HEADLESS}"
                    
                    // Check Java and Maven availability
                    sh 'java -version'
                    sh 'mvn -version'
                    sh 'echo "JAVA_HOME: $JAVA_HOME"'
                    sh 'which java'
                    sh 'which mvn'
                }
            }
        }
        
        stage('Maven Clean') {
            steps {
                echo 'Cleaning previous builds...'
                sh 'mvn clean'
            }
        }
        
        stage('Maven Compile') {
            steps {
                echo 'Compiling the project...'
                sh 'mvn compile test-compile'
            }
        }
        
        stage('Run Tests') {
            steps {
                script {
                    echo 'Running Selenium tests...'
                    
                    // Set environment variables for test execution
                    env.BROWSER = params.BROWSER_TYPE
                    env.HEADLESS = params.RUN_HEADLESS.toString()
                    
                    if (params.TEST_TYPE == 'simple') {
                        echo 'Running Simple Test Suite...'
                        sh """
                            mvn test -Dsurefire.suiteXmlFiles=src/test/resources/simple-testng.xml \
                            -Dbrowser=${params.BROWSER_TYPE} \
                            -Dheadless=${params.RUN_HEADLESS}
                        """
                    } else {
                        echo 'Running Full Test Suite...'
                        sh """
                            mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml \
                            -Dbrowser=${params.BROWSER_TYPE} \
                            -Dheadless=${params.RUN_HEADLESS}
                        """
                    }
                }
            }
            post {
                always {
                    // Archive test results
                    echo 'Archiving test results...'
                    junit '**/target/surefire-reports/*.xml'
                    
                    // Archive screenshots if any
                    script {
                        if (fileExists('screenshots')) {
                            archiveArtifacts artifacts: 'screenshots/**/*.png', allowEmptyArchive: true
                        }
                    }
                }
            }
        }
        
        stage('Generate Reports') {
            steps {
                echo 'Generating test reports...'
                script {
                    // Create reports directory
                    sh 'mkdir -p reports'
                    
                    // Copy surefire reports
                    sh 'cp -r target/surefire-reports/* reports/ 2>/dev/null || :'
                    
                    // Archive reports
                    archiveArtifacts artifacts: 'reports/**/*', allowEmptyArchive: true
                }
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline execution completed!'
            
            // Clean workspace (optional)
            // cleanWs()
        }
        
        success {
            echo 'All tests passed successfully! ✅'
            
            // Send success notification (configure as needed)
            script {
                if (env.CHANGE_ID) {
                    // For Pull Requests
                    echo "PR #${env.CHANGE_ID} tests passed"
                } else {
                    // For branch builds
                    echo "Branch ${env.BRANCH_NAME} tests passed"
                }
            }
        }
        
        failure {
            echo 'Tests failed! ❌'
            
            // Archive failure artifacts
            script {
                if (fileExists('screenshots')) {
                    archiveArtifacts artifacts: 'screenshots/**/*.png', allowEmptyArchive: true
                }
                if (fileExists('logs')) {
                    archiveArtifacts artifacts: 'logs/**/*.log', allowEmptyArchive: true
                }
            }
            
            // Send failure notification (configure as needed)
            echo 'Failure notification would be sent here'
        }
        
        unstable {
            echo 'Some tests are unstable! ⚠️'
        }
    }
}
