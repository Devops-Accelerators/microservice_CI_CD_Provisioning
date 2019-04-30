def call(String microserviceName) {

    sh """
cat <<EOF > rbac-config.yaml
apiVersion: v1
kind: ServiceAccount
metadata:
  name: tiller
  namespace: kube-system
---
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: tiller
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: cluster-admin
subjects:
  - kind: ServiceAccount
    name: tiller
    namespace: kube-system
EOF

kubectl apply -f rbac-config.yaml

helm init --service-account tiller --upgrade

rm rbac-config.yaml

helm install -n ${miroserviceName} helmchart --dry-run --debug
"""
    

}
