def call (String dockerImage){


	
	sh" echo ${dockerImage}"
	
sh """
sed -i "s/nginx/saumyaprashar/${dockerImage} /g" helmchart/values.yaml
"""
	 
  }
