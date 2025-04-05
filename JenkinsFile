pipeline {
  agent any

  tools {
    maven 'maven399'
  }

  stages {
    stage('Git Pull') {
      steps {
        echo '========= Git Pull ========'
        git branch: 'main', credentialsId: 'jenkins-build',
        url: 'https://github.com/aksk179/boot-study.git'
      }
    }
    stage('Maven_Build') {
      steps {
        echo '======== Maven_Build ========'
        sh '''
        mvn clean package -DskipTests
        ls -l
        '''
      }
    }
    stage('Docker_Build&Deploy') {
      steps {
        echo '======== Docker_Build&Deploy ========'
        sh '''
        docker build -t boot-study:v1.0 .
        docker tag boot-study:v1.0 harbor.sjkang.com:5443/my-app/boot-study:v1.0
        docker images
        docker push harbor.sjkang.com:5443/my-app/boot-study:v1.0
        '''
      }
    }
    // stage('Docker_Run') {
    //   steps {
    //     echo '======== Docker_Run ========'
    //     sh '''
    //     docker run -p 38081:8080 harbor.sjkang.com:5443/my-app/boot-study:v1.0 --name boot-study
    //     '''
    //   }
    // }
    stage('Docker_ComposeRun') {
      steps {
        echo '======== Docker_ComposeRun ========'
        sh '''
        /usr/local/bin/docker-compose down
        /usr/local/bin/docker-compose up -d
        '''
      }
    }
  }
}