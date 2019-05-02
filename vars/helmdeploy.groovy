def call(String s) {

    sh """
cat >> rbac-config.yaml << EOF
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
    namespace: kube-system"""

sh """
kubectl apply -f rbac-config.yaml

rm rbac-config.yaml

helm init --service-account tiller --upgrade

sleep 10

helm install -n ${s} helmchart --dry-run"""
}
