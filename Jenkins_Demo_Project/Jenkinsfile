pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                bat 'cd Jenkins_Demo_Project && mvn clean test'
            }
        }
    }
}