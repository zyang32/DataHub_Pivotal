pipeline {
  agent any
  stages {
    stage('CodeRepo') {
      steps {
        git(url: 'https://github.com/shirishphatangare/DataHub_Pivotal.git', branch: 'master', credentialsId: 'c9c7bff0-1406-4752-8df9-560ea6759c9d')
      }
    }
  }
}