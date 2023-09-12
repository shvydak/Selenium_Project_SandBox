pipeline {
  agent any
  stages {
    stage('Clone Repository') {
      steps {
        git(url: 'https://github.com/shvydak/smoove_io.git', branch: 'main')
      }
    }



            stage('Run App') {
              steps {
                withGradle() {
                  bat 'gradlew clean -Dgroups=smoke -Dsuite=login runCustomTests'
                }
              }
            }

            stage('Add New Contact Tests') {
              steps {
                withGradle() {
                  bat 'gradlew clean -Dgroups=smoke -Dsuite=contacts runCustomTests'
                }
              }
            }

            stage('New Email Campaign Saving Tests') {
              steps {
                withGradle() {
                  bat 'gradlew clean -Dgroups=smoke -Dsuite=emailCampaignsSaving runCustomTests'
                }
              }
            }

            stage('Email Campaign Sending Tests') {
              steps {
                withGradle() {
                  bat 'gradlew clean -Dgroups=smoke -Dsuite=emailCampaignsSending runCustomTests'
                }
              }
            }

            stage('Email Campaign Deleting Tests') {
              steps {
                withGradle() {
                  bat 'gradlew clean -Dgroups=smoke -Dsuite=emailCampaignsDeleting runCustomTests'
                }
              }
            }

            stage('SMS Campaign Saving Tests') {
              steps {
                withGradle() {
                  bat 'gradlew clean -Dgroups=smoke -Dsuite=smsCampaignsSaving runCustomTests'
                }
              }
            }
  }
}