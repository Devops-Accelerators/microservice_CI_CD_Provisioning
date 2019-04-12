def workspace;
def branch;
def appDeployProcess;
def imageName;
def configserveruri='';

node {
    stage('Checkout Code')
    {
			checkout scm
			workspace = pwd ()
			branch=sh(returnStdout: true, script: '''git symbolic-ref  HEAD''').trim()
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
    }
    
     stage ('Push Image to Docker Registry')
    { 
    }
    
    stage ('Deploy to Kubernetes')
    { 
    }
    
    
}
		
