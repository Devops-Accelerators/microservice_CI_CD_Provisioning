properties([parameters([[$class: 'GlobalVariableStringParameterDefinition', defaultValue: '', description: '', name: 'MicroserviceName'], [$class: 'GlobalVariableStringParameterDefinition', defaultValue: '', description: '', name: 'apiRepoURL'], [$class: 'GlobalVariableStringParameterDefinition', defaultValue: '', description: '', name: 'port'], credentials(credentialType: 'com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl', defaultValue: '', description: '', name: 'gitCred', required: false)])])

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
				
			    createGithubWebhook(repoName.trim(), props['jenkins.server'], """https://github.com""", githubCredentials )
					
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
					rm -f ${repoName.trim()}/deploy.properties
					rm -f ${repoName}/Dockerfile
					rm -f ${repoName.trim()}/sonar-project.properties
					rm -f ${repoName.trim()}/date.txt
					echo "#second step is done"
					git add . 
					git commit -m "deleting"
					cd ${repoName.trim()}
					#Create deploy.properties file
					cat >> deploy.properties << EOF
deploy.microservice=${microserviceName.trim()}
deploy.port=${port.trim()}"""
					sh """
					cd ${repoName.trim()}
					#Create sonar.properties file
					cat >> sonar-project.properties << EOF
sonar.projectKey=java:${microserviceName.trim()}
sonar.projectName=java:${microserviceName.trim()}
sonar.projectVersion=1.0			
sonar.language=java
sonar.sources=src/main
sonar.sourceEncoding=UTF-8
sonar.java.binaries=target/classes
sonar.test.exclusions=src/test/java/com/mindtree/BasicApp"""
						
					sh """ cd ${repoName.trim()}																
					cp -f ../jenkinsfiles/java.Jenkinsfile Jenkinsfile
					#change pipeline name in Jenkinsfile
					sed -i 's/pipelineName/${microserviceName.trim()}/g'  Jenkinsfile
					cp -f ../Dockerfile Dockerfile
					cp -f ../context.xml context.xml
					cp -f ../tomcat-users.xml tomcat-users.xml
					echo "creating helm chart"
					helm create ${microserviceName.trim()}
					sed -i "s/80/${port.trim()}/g" ${microserviceName.trim()}/templates/deployment.yaml
					sed -i "s/stable/latest/g" ${microserviceName.trim()}/values.yaml
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
	stage ('Execute seed job'){
		build job: "${microserviceName}", propagate: false
	}
}
def createpipelinejob(String jobName, String gitURL)
{
    jobDsl failOnMissingPlugin: true, 
	    sandbox: true,
           scriptText: """pipelineJob("${jobName}") {
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
	sh """curl -v -H "Content-Type:application/json" POST -d \'{ "name": "web", "active": true, "events": ["push"], "config": {"url": "${jenkinsServer}github-webhook/", "content_type": "json"}}\' \\
	${githubApiURL}/repos/Devops-Accelerators/${repoName}/hooks?access_token=${credentials}"""
}

