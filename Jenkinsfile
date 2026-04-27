pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Corrected branch from 'main' to 'master'
                git branch: 'master', url: 'https://github.com/mocha-brownie/Capstone-Project.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                // If tests fail, the pipeline will stop here. 
                // We can add error handling later if you want reports to generate even on test failure.
                bat 'mvn test'
            }
        }

        stage('Report') {
            steps {
                // This relies on the HTML Publisher plugin, which seems to be fine in your setup
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target',
                    reportFiles: 'cucumber-report.html',
                    reportName: 'Cucumber Test Report'
                ])
            }
        }
    }
    // The entire 'post' block containing 'allure' has been deleted to prevent the DSL error.
}