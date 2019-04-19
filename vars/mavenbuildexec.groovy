def call(String arg){
        echo "${arg}"
withEnv( ["PATH+MAVEN=${tool mvn_version}/bin"] ) { 
   
        sh "mvn clean install" 
    } 
    }
