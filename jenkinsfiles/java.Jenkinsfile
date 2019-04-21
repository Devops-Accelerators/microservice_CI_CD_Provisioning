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
	    sonarexec "sonar analysis.."
    }
    
     stage ('Build and Unit Test Execution')
    {
          testexec "junit testing.."
    }
    
     stage ('Code Coverage')
    { 
        codecoveragexec "code coverage execution.."
    }
    stage ('create war')
    {
    	mavenbuildexec "mvn build"
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
    	//helmcreate ["${props['deploy.microservice']}", "${dockerImage}"]
	
	sed -i "s/stable/${BUILD_NUMBER}" helmchart/values.yaml
	sed -i "s/80/${props['deploy.port']}" helmchart/templates/deployment.yaml
    }
	
}
		
