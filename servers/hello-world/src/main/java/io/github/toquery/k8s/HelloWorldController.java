package io.github.toquery.k8s;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class HelloWorldController {

    @Resource
    private AppConfig appConfig;

    @Resource
    private DiscoveryClient discoveryClient;

    @RequestMapping("/")
    public String hello() {
        return "Hello World";
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
