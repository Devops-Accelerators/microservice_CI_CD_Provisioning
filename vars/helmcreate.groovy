def call (String dockerImage){


	
	
	 echo path="saumyaprashar\/${dockerImage}"
sh """
sed -i "s/nginx/${path} /g" helmchart/values.yaml
"""
	 
  }
