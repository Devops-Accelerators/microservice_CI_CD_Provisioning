def call (String dockerImage){


	
	
	 echo path="saumyaprashar/${dockerImage}"
sh """
sed 's/nginx/${path}/' helmchart/values.yaml
"""
	 
  }
