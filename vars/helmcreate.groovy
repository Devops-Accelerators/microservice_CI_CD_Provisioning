def call (String dockerImage){


	
	sh" echo ${dockerImage}"
	
sh """
sed -i "s/nginx/${dockerImage} /g" helmchart/values.yaml
"""
	 
  }
