pipeline {
    agent { 
        label 'windows'
    }
    
    tools {
        maven 'Maven'
    }
    
    stages {
        stage('Build') {
            steps {
                dir('backend/user-service') {
                    echo 'Building...'
                    sh 'mvn clean install'
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
                // Insert test commands, -> mvn test
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying...'
                // Insert deploy commands
            }
        }
    }
}
