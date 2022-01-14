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
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
    public Map<String, Object> hello() throws Exception {
        Map<String, Object> map = new TreeMap<>();
        map.put("app", "Hello World");
        map.put("app.message", appConfig.getMessage());
        map.put("spring.application.name", appName);
        String ip = InetAddress.getLocalHost().getHostAddress();
        map.put("ip", ip);
        List<String> ips = getAllIp();
        map.put("ips", ips);

        String hostName = InetAddress.getLocalHost().getHostName();
        map.put("hostName", hostName);

        Map<String, List<ServiceInstance>> services = this.discoveryClient.getServices().stream()
                .collect(Collectors.toMap(serviceName -> serviceName, serviceName -> discoveryClient.getInstances(serviceName), (oldValue, newValue) -> {
                    oldValue.addAll(newValue);
                    return oldValue;
                }));
        map.put("services", services);
        return map;
    }

    private List<String> getAllIp() throws Exception {
        List<String> ips = new ArrayList<>();
        Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaceEnumeration.hasMoreElements()) {
            NetworkInterface e = networkInterfaceEnumeration.nextElement();
            Enumeration<InetAddress> inetAddressEnumeration = e.getInetAddresses();
            while (inetAddressEnumeration.hasMoreElements()) {
                InetAddress inetAddress = inetAddressEnumeration.nextElement();
                ips.add(inetAddress.getHostAddress());
            }
        }

        return ips;
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
    public ResponseEntity<?> service(@PathVariable("name") String name) {
        List<ServiceInstance> instances = discoveryClient.getInstances(name);
        return ResponseEntity.ok(instances);
    }
}
