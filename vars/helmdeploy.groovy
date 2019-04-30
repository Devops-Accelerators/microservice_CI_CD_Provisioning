def call(String s) {

    sh "echo $s"
    sh "ls -al"
    sh "helm install helmchart"
}
