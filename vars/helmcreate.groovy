def call (String dockerImage){


	
	
	
sh """
sed 's/nginx/${dockerImage}/g' helmchart/values.yaml
"""
	 
  }
