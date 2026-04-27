pipeline {
    agent any

    // Ensure your Jenkins global tools match these names, or remove this block if Maven/Java are configured in your system PATH environment variables
    // tools {
    //     maven 'Maven3' 
    //     jdk 'JDK17'    
    // }

    stages {
        stage('Checkout') {
            steps {
                // Replace this URL with the actual GitHub repository link once you upload the project
                git branch: 'main', url: 'https://github.com/mocha-brownie/Capstone-Project.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                // To prevent the pipeline from failing immediately if a test fails (so reports still generate), 
                // you can wrap this in a catchError or rely on the post block. 
                // For now, keeping it identical to your structure.
                bat 'mvn test'
            }
        }

        stage('Report') {
            steps {
                // Publishes the Cucumber HTML report located in the target directory
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
    
    post {
        always {
            // Since your pom.xml includes the allure-cucumber7-jvm dependency, 
            // this step will process those results into a visual dashboard.
            // (Requires the Allure plugin to be installed in Jenkins)
            allure([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'target/allure-results']]
            ])
        }
    }
}