pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/username/repo.git'
            }
        }
        stage('Build') {
            steps {
                echo 'Building...'
                // Insert build commands here, -> mvn clean install
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
