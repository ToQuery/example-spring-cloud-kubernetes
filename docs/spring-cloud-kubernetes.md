
## 服务端口分布

| 端口 | 服务    |
| ---- | ------- |
| 8010 | account |
| 8011 | movie   |
|      |         |
| 8090 | admin   |
| 8091 | hystrix |
| 8092 | turbine |
| 8093 | zipkin  |


## 部署服务

Kubernetes 暴露服务的方式目前只有三种：LoadBlancer Service、NodePort Service、Ingress

1. 通过 fabric8 插件部署。

```shell script
cd servers/account
mvn clean package fabric8:deploy
```

```shell script
kubectl get services example-spring-cloud-kubernetes-server-account
```

2. 通过yaml 文件部署，

```shell script
cd docs/k8s
kubectl create -f .
```


删除所有
```shell script
kubectl delete -f .
```



