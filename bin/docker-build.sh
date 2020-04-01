#!/bin/bash

set -e
cd ../
docker build -t toquery/example-spring-cloud-kubernetes-cloud-zipkin:0.0.1-SNAPSHOT clouds/zipkin
docker build -t toquery/example-spring-cloud-kubernetes-cloud-admin:0.0.1-SNAPSHOT clouds/admin
docker build -t toquery/example-spring-cloud-kubernetes-hello-world:0.0.1-SNAPSHOT hello-world
docker build -t toquery/example-spring-cloud-kubernetes-server-account:0.0.1-SNAPSHOT servers/account
docker build -t toquery/example-spring-cloud-kubernetes-server-movie:0.0.1-SNAPSHOT servers/movie

