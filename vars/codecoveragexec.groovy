def call(String str)
{
     sh "echo ${str}"
def Sonarscanner = tool 'Sonarscanner';
     withSonarQubeEnv('Sonarqube') {
    sh  """${Sonarscanner}/sonar-scanner -Dsonar.host.url=http://ec2-34-244-155-32.eu-west-1.compute.amazonaws.com -Dsonar.login=admin -Dsonar.password=soumianisoumya@123 -Dsonar.jacoco.reportPaths=target/jacoco.exec"""
     }
}
