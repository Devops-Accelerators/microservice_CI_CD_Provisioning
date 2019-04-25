def call(String imageName){
  dockerImage = docker.build("${imageName}:${BUILD_NUMBER}")
  sh "docker images"
  return dockerImage
  }
