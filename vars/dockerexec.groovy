def call(String microserviceName){
  dockerImage = docker.build("saumyaprashar/${microserviceName}:${BUILD_NUMBER}") 
  sh "docker images"
  sh "echo ${dockerImage}"
  return dockerImage
  }
