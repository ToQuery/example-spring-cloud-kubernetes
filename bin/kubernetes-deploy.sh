#!/bin/bash

set -e

cd ../

# kubectl create -f kubernetes/account-service.yaml
kubectl create -f kubernetes/movie-service.yaml
