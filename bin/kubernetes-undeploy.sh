#!/bin/bash

set -e

kubectl delete deploy,svc account-service
kubectl delete deploy,svc movie-service
