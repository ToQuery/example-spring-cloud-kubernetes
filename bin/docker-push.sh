#!/bin/bash
set -e
docker push toquery/example-spring-cloud-kubernetes-cloud-zipkin:0.0.1
docker push toquery/example-spring-cloud-kubernetes-cloud-gateway:0.0.1
docker push toquery/example-spring-cloud-kubernetes-cloud-admin:0.0.1
docker push toquery/example-spring-cloud-kubernetes-hello-world:0.0.1
docker push toquery/example-spring-cloud-kubernetes-server-account:0.0.1
docker push toquery/example-spring-cloud-kubernetes-server-movie:0.0.1

