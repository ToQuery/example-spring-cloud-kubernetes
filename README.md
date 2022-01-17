# spring-cloud-kubernetes

> 本项目仅讨论 kubernetes 的落地实现，关于各项组件含义配置使用见 [ToQuery/example-kubernetes](https://github.com/ToQuery/example-kubernetes)

## 快速使用 Hello World 服务

```shell script
kubectl apply -f https://raw.githubusercontent.com/ToQuery/example-spring-cloud-kubernetes/master/hello-world/k8s/example-spring-cloud-kubernetes-hello-world-ns.yaml
kubectl apply -f https://raw.githubusercontent.com/ToQuery/example-spring-cloud-kubernetes/master/hello-world/k8s/example-spring-cloud-kubernetes-hello-world-rbac.yaml
kubectl apply -f https://raw.githubusercontent.com/ToQuery/example-spring-cloud-kubernetes/master/hello-world/k8s/example-spring-cloud-kubernetes-hello-world-svc.yaml
kubectl apply -f https://raw.githubusercontent.com/ToQuery/example-spring-cloud-kubernetes/master/hello-world/k8s/example-spring-cloud-kubernetes-hello-world-ingress.yaml
kubectl apply -f https://raw.githubusercontent.com/ToQuery/example-spring-cloud-kubernetes/master/hello-world/k8s/example-spring-cloud-kubernetes-hello-world-cm.yml
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

1. (推荐）通过 Ingress 访问

配置 hosts 文件(`/etc/hosts`)，添加访问域名：

```shell script
cat /etc/hosts

127.0.0.1	example-spring-cloud-kubernetes-hello-world.localhost
```

- 访问 `http://example-spring-cloud-kubernetes-hello-world.localhost` 即可 `Hello World`
- 访问 `http://example-spring-cloud-kubernetes-hello-world.localhost/services` 可查看所有注册的服务
- 访问 `http://example-spring-cloud-kubernetes-hello-world.localhost/services/{name}`  可查看 `name` 服务的信息
- 访问 `http://example-spring-cloud-kubernetes-hello-world.localhost/configs/message` 获取项目配置信息，或k8s的config-map配置


2. 通过 PORT 端口，例如： http://localhost:31730

- 访问 `http://localhost:31730` 即可 `Hello World`
- 访问 `http://localhost:31730/services` 可查看所有注册的服务
- 访问 `http://localhost:31730/services/{name}`  可查看 `name` 服务的信息
- 访问 `http://localhost:31730/configs/message` 获取项目配置信息，或k8s的config-map配置


## Spring Cloud 全栈服务

- 基于 Docker Compose 实现 [spring-cloud-kubernetes.md](doc/spring-cloud-docker-compose.md)

- 基于 Spring Cloud kubernetes 实现 [spring-cloud-kubernetes.md](doc/spring-cloud-kubernetes.md)


