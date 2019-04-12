@Library('my_shared_library')_

properties([parameters([[$class: 'GlobalVariableStringParameterDefinition', defaultValue: '', description: 'Add port for kubernates', name: 'Port'], [$class: 'GlobalVariableStringParameterDefinition', defaultValue: '', description: 'enter the name your microservice', name: 'MicroserviceName'], [$class: 'GlobalVariableStringParameterDefinition', defaultValue: '', description: 'enter git project url', name: 'GitUrl'], credentials(credentialType: 'com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl', defaultValue: '', description: 'enter git project credentials', name: 'GitCredential', required: false)])])

def workspace;
def branch;
def appDeployProcess;
def dockerImage;
def configserveruri='';
def props='';

node {
    stage('Checkout Code')
    {
	checkout scm
	workspace = pwd() 
	     sh "ls -lat"
	FileOutputStream out = new FileOutputStream("seedJob.properties");
	    props.setProperty("microserviceName", "${MicroserviceName}");
	    props.setProperty("port", "${Port}");
	    props.setProperty("gitUrl", "${GitUrl}");
	    props.store(out, null);
	    out.close();
    }
    
    stage ('Static Code Analysis')
    { 
	    sonarexec ""
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
		
