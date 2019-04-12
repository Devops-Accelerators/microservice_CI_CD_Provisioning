def call(String args){
  echo "path=${args}"
  dockerImage = docker.build("saumyaprashar/docker-demo:${BUILD_NUMBER}") 
  sh "docker images"
  return dockerImage
  }
