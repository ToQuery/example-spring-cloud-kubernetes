package io.github.toquery.example.spring.cloud.kubernetes.zipkin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ExampleSpringCloudKubernetesZipkin {

    public static void main(String[] args) {
        SpringApplication.run(ExampleSpringCloudKubernetesZipkin.class, args);
    }
}
