def call() {

    
    sh """ 
    ls -al
    helm install helmchart 
    """
}
