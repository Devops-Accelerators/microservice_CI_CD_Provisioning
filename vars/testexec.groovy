def call(String s){
  sh"echo ${s}"
sh """mvn clean test"""
}
