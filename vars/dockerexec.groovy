def call(String microserviceName){
  dockerImage = docker.build("saumyaprashar/${microserviceName}:${BUILD_NUMBER}") 
  sh "docker images"
  return dockerImage
  }
