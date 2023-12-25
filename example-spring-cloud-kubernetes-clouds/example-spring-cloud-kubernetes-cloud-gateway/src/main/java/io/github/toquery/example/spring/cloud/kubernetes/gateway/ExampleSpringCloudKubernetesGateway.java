package io.github.toquery.example.spring.cloud.kubernetes.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ExampleSpringCloudKubernetesGateway {

    public static void main(String[] args) {
        SpringApplication.run(ExampleSpringCloudKubernetesGateway.class, args);
    }
}
