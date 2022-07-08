@Library("veronica-jenkins-shared-library") _

def infraAgentLabel = "contabo-vps"

pipeline {
    agent {  label infraAgentLabel }
    options {
        skipDefaultCheckout()
        buildDiscarder(logRotator(numToKeepStr: '20'))
        timeout(time: 10, unit: "MINUTES")
    }
    tools {
        maven "MAVEN_3.8.6"
    }
    parameters {
        string(name:'TAG_NAME', defaultValue:'develop', description:'')
    }
    environment {
        TAG_NAME = "${params.TAG_NAME}"
        JAVA_HOME = "/usr/lib/jvm/java-11-openjdk-amd64"
    }
    stages {
        stage("Preparation"){
            steps {
                script {
                    flow.init()
                    flow.withStage("Build", {
                        flow.build()
                    })

                    flow.withStage("Testing", {
                        flow.testing()
                    })
                }
            }
        }
        stage("Push to registry") {
            steps {
                script {
                    flow.dockerPush()
                }
            }
        }
        stage("Deploy to development") {
            agent {  label infraAgentLabel }
            steps {
                script {
                    flow.deploy('dev')
                }
            }
        }
        stage("Deploy to sandbox") {
            agent {  label infraAgentLabel }
            steps {
                script {
                    flow.deploy('sbox')
                }
            }
        }
        stage("Deploy to prod") {
            agent {  label "veronica-tasks-runner" }
            steps {
                script {
                    flow.deploy('prod')
                }
            }
        }
    }
    post {
        always {
            archiveArtifacts 'target/*.jar'
            //junit '**/surefire-reports/**/*.xml'
            cleanWs()
        }
    }
}
