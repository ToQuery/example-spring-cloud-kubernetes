#!/bin/bash

set -e

cd ../
kubectl create -f kubernetes/example-spring-cloud-kubernetes-namespace.yaml

kubectl create -f kubernetes/example-spring-cloud-kubernetes-hello-world.yaml
kubectl create -f kubernetes/example-spring-cloud-kubernetes-server-account.yaml
kubectl create -f kubernetes/example-spring-cloud-kubernetes-server-movie.yaml
