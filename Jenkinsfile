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
                                                            echo ${username%@*} ''').trim();
			commit_username=sh(returnStdout: true, script: """echo ${commit_username} | sed 's/48236651+//g'""").trim()
				repoName=sh(returnStdout: true, script: """echo \$(basename ${apiRepoURL.trim()})""").trim();
				repoName=sh(returnStdout: true, script: """echo ${repoName} | sed 's/.git//g'""").trim()
			sh"""echo ${repoName}
			echo ${commit_username}
				"""
			}
     
 stage ('Create CI Pipeline')
		{
				
							createpipelinejob(microserviceName.trim(), apiRepoURL.trim())
						
					
		}
	stage ('Create CD Pipeline')
		{
				
		}
	stage ('Add pipeline Scripts to Repository')
		{
			withCredentials([usernameColonPassword(credentialsId: 'jenkinsadminCredentials', variable: 'jenkinsAdminCredentials')]) 
					{
					sh """ rm -rf ${repoName.trim()}
					git clone ${apiRepoURL}""".trim()
						echo "Cloning is done here"
						//add app name and definition file name
								
								sh """
								rm -f ${repoName.trim()}/Jenkinsfile
								echo "#second step is done"
								
								cd ${repoName.trim()}
								 """

								sh """ cd ${repoName.trim()}
																					
								cp -f ../jenkinsfiles/java.Jenkinsfile Jenkinsfile	
								#change pipeline name in Jenkinsfile
								sed -i 's/pipelineName/${microserviceName.trim()}/g'  Jenkinsfile	
	
								git config user.name ${commit_username}
								git add .
								git commit -m "pipeline Scripts added by seed job"
								git push -f origin master
								cd ..
								rm -rf ${repoName.trim()}"""
					
			}
		}
}
def createpipelinejob(String jobName, String gitURL)
{
    jobDsl failOnMissingPlugin: true, 
	    sandbox: true,
           scriptText: """pipelineJob("${jobName}") {
                            
                          definition {
                        			cpsScm {
                        					scm {
                        						git {
                        								remote {
                        									name('remoteB')
                        									url('${gitURL}')
												credentials('${gitCred}')
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
