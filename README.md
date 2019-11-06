# spring-cloud-kubernetes

## 快速使用

快速查看 Hello World 服务

```shell script
cd servers/hello-world
mvn clean package fabric8:deploy
```
等待服务部署完毕后，查看服务的部署情况

```shell script
kubectl get services example-spring-cloud-kubernetes-server-hello-world
```

会得到如下信息：

```text
NAME                                                 TYPE       CLUSTER-IP    EXTERNAL-IP   PORT(S)          AGE
example-spring-cloud-kubernetes-server-hello-world   NodePort   10.102.61.4   <none>        8080:31730/TCP   51m
```
注意 `EXTERNAL-IP` ，目前还是none,也就是存在VIP，还没有暴露ip。这里涉及 services 的 type 了，现在使用的 NodePort。
转为 `LoadBalancer` 就可以了。

```shell script
kubectl expose deployment example-spring-cloud-kubernetes-server-hello-world --type=LoadBalancer --name=example-spring-cloud-kubernetes-server-hello-world-load-balancer
```
等待一分钟，查看 services 会多一个新服务了 `spring-cloud-kubernetes-server-hello-world-load-balancer`
```shell script
kubectl get services spring-cloud-kubernetes-server-hello-world-load-balancer
```

```shell script
NAME                                                       TYPE           CLUSTER-IP      EXTERNAL-IP   PORT(S)                                        AGE
spring-cloud-kubernetes-server-hello-world-load-balancer   LoadBalancer   10.101.144.30   localhost     8080:30683/TCP,9779:31802/TCP,8778:30643/TCP   34s
```

- 访问 `http://localhost:8080` 即可 `Hello World`
- 访问 `http://localhost:8080/services` 可查看所有注册的服务