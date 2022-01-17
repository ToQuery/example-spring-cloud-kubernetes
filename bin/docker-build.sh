#!/bin/bash

set -e
cd ../

docker build -t toquery/example-spring-cloud-kubernetes-hello-world:0.0.1 hello-world

docker build -t toquery/example-spring-cloud-kubernetes-cloud-zipkin:0.0.1 clouds/zipkin
docker build -t toquery/example-spring-cloud-kubernetes-cloud-admin:0.0.1 clouds/admin
docker build -t toquery/example-spring-cloud-kubernetes-cloud-gateway:0.0.1 clouds/gateway

docker build -t toquery/example-spring-cloud-kubernetes-server-account:0.0.1 servers/account
docker build -t toquery/example-spring-cloud-kubernetes-server-movie:0.0.1 servers/movie

docker rmi $(docker images -f "dangling=true" -q)
