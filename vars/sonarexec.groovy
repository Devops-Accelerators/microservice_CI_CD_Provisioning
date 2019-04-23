def call(String s) {
       def Sonarscanner = tool 'Sonarscanner';
    withSonarQubeEnv('Sonarqube') 
    {
           sh "echo ${s}" 
        sh """echo ${Sonarscanner}"""
     sh  """${Sonarscanner}/sonar-scanner -Dsonar.host.url=http://ec2-54-229-131-76.eu-west-1.compute.amazonaws.com -Dsonar.login=admin -Dsonar.password=soumianisoumya@123"""
        
    }
    
        }
