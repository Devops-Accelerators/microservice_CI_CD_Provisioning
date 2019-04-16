properties([parameters([[$class: 'GlobalVariableStringParameterDefinition', defaultValue: '', description: '', name: 'port'], [$class: 'GlobalVariableStringParameterDefinition', defaultValue: '', description: '', name: 'MicroserviceName'], [$class: 'GlobalVariableStringParameterDefinition', defaultValue: '', description: '', name: 'apiRepoURL'], password(defaultValue: '', description: '', name: 'gitPassword'), credentials(credentialType: 'com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl', defaultValue: '', description: '', name: 'gitCred', required: false)])])
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
			sh"""echo ${repoName}"""
			}
    
 stage ('Create CI Pipeline')
		{				
			createpipelinejob(microserviceName.trim(), apiRepoURL.trim())		
		}
	
  stage ('Add Repo Webhook')
		{
			withCredentials([string(credentialsId: 'githubtoken', variable: 'githubCredentials'),
			usernameColonPassword(credentialsId: 'jenkinsadminCredentials', variable: 'jenkinsAdminCredentials')]) 
			{
				
			    createGithubWebhook(repoName.trim(), props['jenkins.server'], """https://github.com/Devops-Accelerators""", githubCredentials )
					
			}
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
					rm -f ${repoName}/Jenkinsfile
					rm -rf ${microserviceName.trim()}
					echo "#second step is done"
					git add . 
					git commit -m "deleting"
					cd ${repoName.trim()}
					 """
					sh """ cd ${repoName.trim()}																
					cp -f ../jenkinsfiles/java.Jenkinsfile Jenkinsfile
					#change pipeline name in Jenkinsfile
					sed -i 's/pipelineName/${microserviceName.trim()}/g'  Jenkinsfile
					echo "creating helm chart"
					helm create ${microserviceName.trim()}
					cp -rf ${microserviceName.trim()}/ helmchart
					echo "remove helm chart"
					rm -rf ${microserviceName.trim()}
					git config --global user.name ${commit_username}
					git init
					git add .
					git pull
					git commit -m "pipeline Script added by seed job"
					git remote rm origin
					git remote add origin ${apiRepoURL}
					git remote -v
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
	   parameters {
                         stringParam("Port", "${port}", "")
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

def createGithubWebhook(String repoName, String jenkinsServer, String githubApiURL, String credentials)
{
	sh """curl -v -H "Content-Type: application/json" POST -d \'{ "name": "web", "active": true, "events": ["push"], "config": {"url": "${jenkinsServer}github-webhook/", "content_type": "json"}}\' \\
	${githubApiURL}/${repoName}/settings/hooks?access_token=${credentials}"""
}

