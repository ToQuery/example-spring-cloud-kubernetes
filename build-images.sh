#!/bin/bash

set -e
docker build -t toquery/config-server ./config-server
docker build -t toquery/service-registry ./service-registry
docker build -t toquery/turbine-server ./turbine-server
docker build -t toquery/monitor-dashboard ./monitor-dashboard
docker build -t toquery/auth-service ./auth-service
docker build -t toquery/account-service ./account-service
docker build -t toquery/cloud-gateway ./cloud-gateway

docker rmi $(docker images -f "dangling=true" -q)
