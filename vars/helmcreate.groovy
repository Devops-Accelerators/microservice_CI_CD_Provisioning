def call (List<String> param){


	sh "echo ${param[0]} "
/*sh """
sed -i "s/nginx/${props['deploy.dockerImage']} /g" ${microserviceName}/values.yaml
sed -i "s/stable/latest/g" ${microserviceName}/values.yaml
sed -i "s/80/${props['deploy.container_port']} /g" ${microserviceName}/templates/deployment.yaml
"""*/
	//sh 'chmod +x helmcreate.sh'
	//sh './helmcreate.sh'
	//sh 'rm helmcreate.sh'
	 
  }
