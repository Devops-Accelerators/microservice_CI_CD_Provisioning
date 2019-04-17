def call(String s){
  echo ${s}
sh """mvn clean test"""
}
