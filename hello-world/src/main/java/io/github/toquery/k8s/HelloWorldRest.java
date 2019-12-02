package io.github.toquery.k8s;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class HelloWorldRest {

    @Resource
    private AppConfig appConfig;

    @Value("${spring.application.name:example-spring-cloud-kubernetes-hello-world}")
    private String appName;

    @Resource
    private DiscoveryClient discoveryClient;

    @RequestMapping("/")
    public Map<String, Object> hello() {
        Map<String, Object> map = new HashMap<>();
        map.put("app", "Hello World");
        map.put("app.message", appConfig.getMessage());
        map.put("spring.application.name", appName);
        Map<String, List<ServiceInstance>> services = this.discoveryClient.getServices().stream()
                .collect(Collectors.toMap(item -> item, item -> discoveryClient.getInstances(item), (oldValue, newValue) -> {
                    oldValue.addAll(newValue);
                    return oldValue;
                }));
        map.put("services", services);
        return map;
    }


    @RequestMapping("/configs/message")
    public String configs() {
        return appConfig.getMessage();
    }

    @RequestMapping("/services")
    public List<String> services() {
        return this.discoveryClient.getServices();
    }


    @GetMapping("/services/{name}")
    public ResponseEntity service(@PathVariable("name") String name) {
        List<ServiceInstance> instances = discoveryClient.getInstances(name);
        return ResponseEntity.ok(instances);
    }
}
