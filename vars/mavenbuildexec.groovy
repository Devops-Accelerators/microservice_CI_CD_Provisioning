def call(){
withEnv( ["PATH+MAVEN=${tool mvn_version}/bin"] ) { 

        sh "mvn clean install" 
    } 
    }
