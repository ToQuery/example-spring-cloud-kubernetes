#!/bin/bash

set -e

cd ../

# 创建命名空间
kubectl create -f hello-world/k8s/example-spring-cloud-kubernetes-hello-world-ns.yaml
# 创建角色访问资源
kubectl create -f hello-world/k8s/example-spring-cloud-kubernetes-hello-world-rbac.yaml
# 创建服务
kubectl create -f hello-world/k8s/example-spring-cloud-kubernetes-hello-world-svc.yaml
# 创建访问入口
kubectl create -f hello-world/k8s/example-spring-cloud-kubernetes-hello-world-ingress.yaml
# 创建配置
kubectl create -f hello-world/k8s/example-spring-cloud-kubernetes-hello-world-cm.yml
