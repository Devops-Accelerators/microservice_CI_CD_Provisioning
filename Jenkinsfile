properties([parameters([[$class: 'GlobalVariableStringParameterDefinition', defaultValue: '', description: '', name: 'port'], [$class: 'GlobalVariableStringParameterDefinition', defaultValue: '', description: '', name: 'MicroserviceName'], [$class: 'GlobalVariableStringParameterDefinition', defaultValue: '', description: '', name: 'apiRepoURL'], credentials(credentialType: 'com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl', defaultValue: '', description: '', name: 'gitCred', required: false)])])

def branchName;
def newJobname;
def addedProjects;
def gitURL;
def props;
def jobsCreated="";
def status;
def ucdjobList;
def microserviceName;
def newjob=true;
def createJIRA=false;
def commit_username;
def commit_Email, repoName, envConfigProp;
node { 
	stage ('Checkout Code')
		{
				checkout scm
        workspace = pwd ()
				props = readProperties  file: """seedJob.properties"""
        microserviceName = sh(returnStdout: true, script: """echo ${MicroserviceName} | sed 's/[\\._-]//g'""").trim()
				microserviceName = microserviceName.toLowerCase()
			sh"""echo ${microserviceName}""" 
				commit_username=sh(returnStdout: true, script: '''username=$(git log -1 --pretty=%ae) 
                                                            echo ${username%@*}''').trim();
				commit_Email=sh(returnStdout: true, script: '''Email=$(git log -1 --pretty=%ae) 
                                                            echo $Email''').trim();
				repoName=sh(returnStdout: true, script: """echo \$(basename ${apiRepoURL.trim()} .git)""").trim();
			sh"""echo ${commit_username}
				echo ${commit_Email}"""
			}
     
 stage ('Create CI Pipeline')
		{
				
							createpipelinejob(microserviceName.trim(), apiRepoURL.trim())
						
					
		}
	stage ('Create CD Pipeline')
		{
				
		}
}
def createpipelinejob(String jobName, String gitURL)
{
    jobDsl failOnMissingPlugin: true, 
           ignoreExisting: true, 
           sandbox: true, 
           scriptText: """pipelineJob("${jobName}") {
                            parameters {
                                stringParam("stageExecution", "deploy", "")
                                }
                        	triggers {
                                githubPush()
                        		}
                          definition {
                        			cpsScm {
                        					scm {
                        						git {
                        								remote {
                        									name('remoteB')
                        									url('${gitURL}')
                        									}
                        									branch("*/master")
                        									extensions {}	
                        									}	
                        							}
                        							scriptPath("Jenkinsfile")	
                        						}
                        			}
                        }"""
}
