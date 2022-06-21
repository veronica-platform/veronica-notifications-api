@Library("veronica-jenkins-shared-library") _

def infraAgentLabel = "infra"

pipeline {
    agent {  label infraAgentLabel }
    options {
        skipDefaultCheckout()
        buildDiscarder(logRotator(numToKeepStr: '20'))
        timeout(time: 10, unit: "MINUTES")
    }
    tools {
        maven "MAVEN_3.8.3"
    }
    parameters {
        string(name:'TAG_NAME', defaultValue:'develop', description:'')
    }
    environment {
        TAG_NAME = "${params.TAG_NAME}"
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
    }
    post {
        always {
            archiveArtifacts 'target/*.jar'
            //junit '**/surefire-reports/**/*.xml'
            cleanWs()
        }
    }
}
