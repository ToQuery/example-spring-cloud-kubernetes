package io.github.toquery.k8s.rest;

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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class TestRest {


    @Value("${spring.application.name:example-spring-cloud-kubernetes-account}")
    private String appName;

    @Resource
    private DiscoveryClient discoveryClient;

    @RequestMapping("/")
    public Map<String, Object> hello() throws Exception {
        Map<String, Object> map = new TreeMap<>();
        map.put("app", "Hello World");
        map.put("spring.application.name", appName);
        String ip = InetAddress.getLocalHost().getHostAddress();
        map.put("ip", ip);
        List<String> ips = getAllIp();
        map.put("ips", ips);

        String hostName = InetAddress.getLocalHost().getHostName();
        map.put("hostName", hostName);

        List<String> services = this.services();
        map.put("services", services);
        map.put("serviceDetails", services.stream().map(service -> "/service/" + service).collect(Collectors.toList()));
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

    @RequestMapping("/services")
    public List<String> services() {
        return this.discoveryClient.getServices();
    }


    @RequestMapping("/service/{serviceId}")
    public List<ServiceInstance> serviceDetails(@PathVariable String serviceId) {
        return this.discoveryClient.getInstances(serviceId);
    }


    @GetMapping("/services/{name}")
    public ResponseEntity<?> service(@PathVariable("name") String name) {
        List<ServiceInstance> instances = discoveryClient.getInstances(name);
        return ResponseEntity.ok(instances);
    }
}
