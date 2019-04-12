@Library('my_shared_library')_
def workspace;
def branch;
def appDeployProcess;
def dockerImage;
def configserveruri='';

node {
    stage('Checkout Code')
    {
			checkout scm
			workspace = pwd ()
			pwd
    }
    
    
    stage ('Static Code Analysis')
    { 
    }
    
     stage ('Build and Unit Test Execution')
    {
    }
    
     stage ('Code Coverage')
    { 
    }
    
     stage ('Create Docker Image')
    { 
	     echo 'creating an image'
             dockerImage = dockerexec "/var/lib/jenkins/workspace/DockerDemo/"
    }
    
     stage ('Push Image to Docker Registry')
    { 
	     docker.withRegistry('https://registry.hub.docker.com','docker-credentials') {
             dockerImage.push("${BUILD_NUMBER}")
             dockerImage.push("latest")
	     }
    }
    
    stage ('Deploy to Kubernetes')
    { 
    }
	
    stage ('add pipeline to github repo')
    { 
    }
	
     stage ('Create job')
    { 
    }
	
}
		
