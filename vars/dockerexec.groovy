def call(String microserviceName){
  dockerImage = docker.build("saumyaprashar/${microserviceName}:${BUILD_NUMBER}") 
  dockerImage1 = "saumyaprashar/${microserviceName}"
  sh "docker images"
  return dockerImage
  }
