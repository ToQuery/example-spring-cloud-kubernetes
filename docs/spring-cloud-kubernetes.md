
## 服务

- [] mongodb
- [] kong
- [] konga
- [] prometheus
- [] grafana
- [] elk

## 服务器地址路径与内部服务、端口

| 服务名称 | 域名路径                     | 端口 | 服务    |      |
| -------- |--------------------------| ---- | ------- | ---- |
| kong网关 | http://kong.local        | 8010 | ui      |      |
| Konga    | http://konga.local | 8011 | account |      |
| Grafana | http://grafana.local | 8012 | movie   |      |
| kibana | http://kibana.local |      |         |      |
|          |                          | 8090 | admin   |      |
|          |                          | 8093 | zipkin  |      |


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



