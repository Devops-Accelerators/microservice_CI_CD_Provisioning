def call (String dockerImage){


	
	prop = readProperties  file: """deploy.properties"""
	sh" echo ${dockerImage}"
	sh" echo prop['deploy.microservice']"
	
/*sh """
sed -i "s/nginx/${dockerImage} /g" prop['deploy.microservice']/values.yaml
sed -i "s/stable/latest/g" prop['deploy.microservice']/values.yaml
sed -i "s/80/prop['deploy.container_port'] /g" prop['deploy.microservice']/templates/deployment.yaml
"""*/
	//sh 'chmod +x helmcreate.sh'
	//sh './helmcreate.sh'
	//sh 'rm helmcreate.sh'
	 
  }
