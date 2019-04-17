def call(String microserviceName){
  dockerImage = docker.build("saumyaprashar/${microserviceName}:${BUILD_NUMBER}") 
  dockerImage1 = "saumyaprashar/${microserviceName}"
  sh "docker images"
  sh "echo ${dockerImage1}"
  return dockerImage1
  }
