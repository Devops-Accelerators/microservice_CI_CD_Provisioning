@Library('my_shared_library')_

def workspace;
def branch;
def appDeployProcess;
def dockerImage;
def configserveruri='';
def props='';
def microserviceName;
def port;
def gitUrl;
def repoName;
def Cusername;
def Cemail;
def credentials = 'docker-credentials';

node {
    stage('Checkout Code')
    {
	checkout scm
	workspace = pwd() 
	     sh "ls -lat"
	props = readProperties  file: """deploy.properties"""   
    }
    
    stage ('Static Code Analysis')
    { 
	    sonarexec
    }
    
     stage ('Build and Unit Test Execution')
    {
          testexec
    }
    
     stage ('Code Coverage')
    { 
        codecoveragexec
    }
    
     stage ('Create Docker Image')
    { 
	     echo 'creating an image'
             dockerImage = dockerexec "${props['deploy.microservice']}"
    }
    
     stage ('Push Image to Docker Registry')
    { 
	     docker.withRegistry('https://registry.hub.docker.com',credentials) {
             dockerImage.push("${BUILD_NUMBER}")
             dockerImage.push("latest")
	     }
    }
    
    stage ('Config helm')
    { 
    	sh "echo 'Almost there'"
    	//helmcreate ["${props['deploy.microservice']}","${props['deploy.port']}", "${dockerImage}"]
    }
	
}
		
