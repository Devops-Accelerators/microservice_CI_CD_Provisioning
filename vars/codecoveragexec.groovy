def call(String str)
{
     sh "echo ${str}"
def Sonarscanner = tool 'Sonarscanner';
     withSonarQubeEnv('Sonarqube') {
    sh  """${Sonarscanner}/sonar-scanner -Dsonar.host.url=${str} -Dsonar.login=admin -Dsonar.password=soumianisoumya@123 -Dsonar.jacoco.reportPaths=target/jacoco.exec"""
     }
}
