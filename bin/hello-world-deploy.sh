#!/bin/bash

set -e

cd ../

kubectl create -f docs/k8s/example-spring-cloud-kubernetes-hello-world.yaml
