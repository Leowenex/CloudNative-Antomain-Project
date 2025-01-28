# Start a sufficent Minikube cluster

minikube start --memory=8192mb --cpus=4

# Needed plugins

minikube addons enable ingress
minikube addons enable istio-provisioner
minikube addons enable istio

# Deploy helm chart

helm upgrade --install cnap antomain-helm

# Forwarding Istio Gateway to the service

kubectl -n istio-system port-forward deployment/istio-ingressgateway 31380:8080