pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                // Clone the repository
                git branch: 'develop', url: 'https://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@github.com/SumangalaBhat/Edureka_Certificate_Repo.git'
            }
        }
        stage('Compile') {
            steps {
                // Run Maven clean package
                sh 'mvn compile'
                echo 'code compile completed'
            }
        }
        stage('Test') {
            steps {
                // Run Maven tests
                sh 'mvn test'
                echo 'code testing completed'
            }
        }
        stage('Package') {
            steps {
                // Package the application into a JAR/WAR file
                sh 'mvn package'
                echo 'Packaging completed'
            }
        }
    }
}
