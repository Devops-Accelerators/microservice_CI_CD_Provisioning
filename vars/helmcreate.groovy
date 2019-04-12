def call (){

#create the helm chart
sh "helm create ${microserviceName}"
sh """
	cat <<EOF > helmcreate.sh
#!/bin/bash
sed -i "s/nginx/${image}/g" ${chart_name}/values.yaml
sed -i "s/stable/${tag}/g" ${chart_name}/values.yaml
sed -i "s/80/${container_port}/g" ${chart_name}/templates/deployment.yaml
EOF"""
	sh 'chmod +x helmcreate.sh'
	sh './helmcreate.sh'
	sh 'rm helmcreate.sh'
	 
  }
