#!/bin/bash

set -e

# use namespace
kubectl config set-context --current --namespace example-spring-cloud-kubernetes

kubectl delete deploy,svc example-spring-cloud-kubernetes-hello-world
kubectl delete deploy,svc example-spring-cloud-kubernetes-server-movie
kubectl delete deploy,svc example-spring-cloud-kubernetes-server-account

# delete namespace
kubectl delete ns example-spring-cloud-kubernetes
