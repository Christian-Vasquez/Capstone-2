pipeline {
    agent any

    stages {

        stage('build') {
            steps {
              sh '''
                 cd ./admin-service
                 ./mvnw -DskipTests clean compile
              '''
            }
        }

        stage('test') {
            steps {
              sh '''
                 cd admin-service
                     ./mvnw test
              '''
            }
        }

        stage('deliver') {
            steps {
              sh '''
                 cd admin-service
                     ./mvnw -DskipTests install
              '''
            }
        }

        stage('build') {
            steps {
              sh '''
                 cd ./retail-api-service
                 ./mvnw -DskipTests clean compile
              '''
            }
        }

        stage('test') {
            steps {
              sh '''
                 cd retail-api-service
                     ./mvnw test
              '''
            }
        }

        stage('deliver') {
            steps {
              sh '''
                 cd retail-api-service
                     ./mvnw -DskipTests install
              '''
            }
        }

    }
}