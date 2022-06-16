# Collect metrics in a spring boot project

To collect metrics in a spring boot project deployed in Kubernetes a cluster, I'll use 
Prometheus and Grafana. 

1- Install Prometheus with the Kubernetes package manager (Helm)

* helm repo add bitnami https://charts.bitnami.com/bitnami
* helm repo update
* helm install prometheus bitnami/kube-prometheus

for testing purpose :
* kubectl port-forward --namespace default svc/prometheus-kube-prometheus-prometheus 9090:9090 &

> Check that Prometheus is up and reachable : http://localhost:9090

2- Install Grafana

* helm install grafana bitnami/grafana

Like Prometheus, you can use :
* kubectl port-forward svc/grafana 8080:3000 &

> Check that Grafana is up and reachable : http://localhost:3000

For user & password :
* echo "User: admin"
* echo "Password: $(kubectl get secret grafana-admin --namespace default -o jsonpath="{.data.GF_SECURITY_ADMIN_PASSWORD}" | base64 -d)"

3- Docker image:
* docker run -d -p 5000:5000 --restart=always --name registry registry:2
* docker build . -t localhost:5000/metrics:0.0.1
* docker push localhost:5000/metrics:0.0.1

4- Other Kubernetes commands:

* kubectl create ns metrics
* kubectl apply -f ./k8s