
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

```shell
http://ingress.local/movie/account/feign
http://ingress.local/account/movie/feign

http://ingress.local/movie/account/feign/delay/5
http://ingress.local/account/movie/feign/delay/5

http://ingress.local/movie/account/resilience4j
http://ingress.local/account/movie/resilience4j

http://ingress.local/movie/account/resilience4j/delay/5
http://ingress.local/account/movie/resilience4j/delay/5
```


```shell
http://localhost:8011/account/movie/feign
http://localhost:8012/movie/account/feign

http://localhost:8011/account/movie/feign/delay/5
http://localhost:8012/movie/account/feign/delay/5


http://localhost:8011/account/movie/retry
http://localhost:8012/movie/account/retry

http://localhost:8011/account/movie/retry/delay/5
http://localhost:8012/movie/account/retry/delay/5


http://localhost:8011/account/movie/resilience4j
http://localhost:8012/movie/account/resilience4j

http://localhost:8011/account/movie/resilience4j/delay/5
http://localhost:8012/movie/account/resilience4j/delay/5
```

## 部署服务

Kubernetes 暴露服务的方式目前只有三种：LoadBlancer 、NodePort 、Ingress

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



