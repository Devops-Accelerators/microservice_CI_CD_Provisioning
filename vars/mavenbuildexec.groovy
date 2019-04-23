def call(String arg){
        echo "${arg}"

        sh "mvn clean install" 
    }
