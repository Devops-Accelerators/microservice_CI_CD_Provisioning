def call(String args){
  sh "docker-compose config"
  sh "docker-compose up"
}
