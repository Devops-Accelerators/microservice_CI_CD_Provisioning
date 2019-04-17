def call(String microserviceName){
  dockerImage = docker.build("devopsaccelerator/${microserviceName}:${BUILD_NUMBER}")
  sh "docker images"
  return dockerImage
  }
