#!/bin/bash

set -e

cd ../

kubectl create -f docs/kubernetes/example-spring-cloud-kubernetes-hello-world.yaml
