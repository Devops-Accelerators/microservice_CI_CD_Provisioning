def call() {
        def mvnHome =  tool name: 'mvn1', type: 'maven' 

        withSonarQubeEnv('Sonarqube') {  

          sh "${mvnHome}/bin/mvn sonar:sonar" 
        }
}
