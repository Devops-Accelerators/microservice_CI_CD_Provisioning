def call (String dockerImage){


	
	def prop = readProperties  file: """deploy.properties"""
sh """
sed -i "s/nginx/${dockerImage} /g" prop['deploy.microservice']/values.yaml
sed -i "s/stable/latest/g" prop['deploy.microservice']/values.yaml
sed -i "s/80/prop['deploy.container_port'] /g" prop['deploy.microservice']/templates/deployment.yaml
"""
	//sh 'chmod +x helmcreate.sh'
	//sh './helmcreate.sh'
	//sh 'rm helmcreate.sh'
	 
  }
