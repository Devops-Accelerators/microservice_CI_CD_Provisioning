def call(String args){
  echo "path=${args}"
  dockerImage = docker.build("${microserviceName}:${BUILD_NUMBER}") 
  sh "docker images"
  return dockerImage
  }
