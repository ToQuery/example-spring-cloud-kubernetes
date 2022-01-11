#!/bin/bash

set -e

# use namespace
# kubectl config set-context --current --namespace example-spring-cloud-kubernetes

kubectl create -f ../docs/kubernetes/example-spring-cloud-kubernetes-namespace.yaml
kubectl create -f ../docs/kubernetes/example-spring-cloud-kubernetes-server-account.yaml
kubectl create -f ../docs/kubernetes/example-spring-cloud-kubernetes-server-movie.yaml
kubectl create -f ../docs/kubernetes/example-spring-cloud-kubernetes-ingress.yaml
