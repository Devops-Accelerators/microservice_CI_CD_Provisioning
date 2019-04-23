def call(String arg){
        echo "${arg}"
withEnv( ["PATH+MAVEN=${tool mvn1}/bin"] ) { 
   
        sh "mvn clean install" 
    } 
    }
