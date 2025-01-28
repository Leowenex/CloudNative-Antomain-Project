# Consulter ce README
# https://github.com/charroux/lsi1?tab=readme-ov-file

# Start a sufficent Minikube cluster

minikube start --memory=8192mb --cpus=4

# Install Istio
# If the cli is not installed, follow https://istio.io/latest/docs/setup/getting-started/

istioctl install --set profile=demo -y

kubectl label namespace default istio-injection=enabled

# In istio repo root (downloaded separately) :

kubectl apply -f samples/addons

# Deploy helm chart

helm upgrade --install cnap antomain-helm

# Forwarding Istio Gateway

kubectl -n istio-system port-forward deployment/istio-ingressgateway 31380:8080

# Forwarding Kiali

kubectl -n istio-system port-forward deployment/kiali 20001:20001

# Forwarding Grafana

kubectl -n istio-system port-forward deployment/grafana 3000:3000

# Uninstall helm chart

helm uninstall cnap

# Remove everything

kubectl delete all --all -n istio-system