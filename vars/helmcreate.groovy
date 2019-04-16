def call (String ${microserviceName}, String ${container_port}, String ${dockerImage}){

#mod helm chart
sh """
cat <<EOF > helmcreate.sh
#!/bin/bash
sed -i "s/nginx/${dockerImage}/g" ${microserviceName}/values.yaml
sed -i "s/stable/latest/g" ${microserviceName}/values.yaml
sed -i "s/80/${container_port}/g" ${microserviceName}/templates/deployment.yaml
EOF"""
	sh 'chmod +x helmcreate.sh'
	sh './helmcreate.sh'
	sh 'rm helmcreate.sh'
	 
  }
