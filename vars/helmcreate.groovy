def call (String dockerImage){


	
	sh" echo ${dockerImage}"
	
sh """
sed -i "s/nginx/${dockerImage} /g" prop['deploy.microservice']/values.yaml
"""
	 
  }
