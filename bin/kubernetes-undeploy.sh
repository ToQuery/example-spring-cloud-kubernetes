#!/bin/bash

set -e
kubectl delete deploy,svc example-spring-cloud-kubernetes-hello-world
kubectl delete deploy,svc example-spring-cloud-kubernetes-server-movie
kubectl delete deploy,svc example-spring-cloud-kubernetes-server-account
