# spring-cloud-kubernetes

## 快速使用 Hello World 服务

```shell script
cd hello-world
mvn clean package -Pkubernetes fabric8:deploy
```

等待服务部署完毕后（或通过命令行 `kubectl get pods -w`），查看服务的部署情况

```shell script
kubectl get services example-spring-cloud-kubernetes-hello-world
```

会得到如下信息：

```text
NAME                                          TYPE       CLUSTER-IP    EXTERNAL-IP   PORT(S)          AGE
example-spring-cloud-kubernetes-hello-world   NodePort   10.102.61.4   <none>        8080:31730/TCP   51m
```

这里有两种方式访问服务：

1. （推荐）通过 PORT 端口，例如： http://localhost:31730

- 访问 `http://localhost:31730` 即可 `Hello World`
- 访问 `http://localhost:31730/services` 可查看所有注册的服务
- 访问 `http://localhost:31730/services/{name}`  可查看 `name` 服务的信息
- 访问 `http://localhost:31730/configs/message` 获取项目配置信息，或k8s的config-map配置

2. 将 services 的 type 从 `NodePort` 变更为 `LoadBalancer`

注意 `EXTERNAL-IP` ，目前还是none,也就是存在VIP，还没有外部ip。这里涉及 services 的 type 了，现在使用的 NodePort，转为 `LoadBalancer` 就可以了。

```shell script
kubectl expose deployment example-spring-cloud-kubernetes-hello-world --type=LoadBalancer --name=example-spring-cloud-kubernetes-hello-world-load-balancer
```

等待一分钟，查看 services 会多一个新服务了 `example-spring-cloud-kubernetes-hello-world-load-balancer`

```shell script
kubectl get services example-spring-cloud-kubernetes-hello-world-load-balancer
```

```shell script
NAME                                                        TYPE           CLUSTER-IP      EXTERNAL-IP   PORT(S)                                        AGE
example-spring-cloud-kubernetes-hello-world-load-balancer   LoadBalancer   10.101.144.30   localhost     8080:30683/TCP,9779:31802/TCP,8778:30643/TCP   34s
```

## Spring Cloud 全栈服务

doc/spring-cloud-kubernetes.md


