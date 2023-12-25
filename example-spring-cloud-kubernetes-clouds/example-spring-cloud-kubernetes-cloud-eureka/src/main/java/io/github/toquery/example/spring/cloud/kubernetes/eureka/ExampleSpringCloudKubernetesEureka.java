package io.github.toquery.example.spring.cloud.kubernetes.eureka;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ExampleSpringCloudKubernetesEureka {

    public static void main(String[] args) {
        SpringApplication.run(ExampleSpringCloudKubernetesEureka.class, args);
    }
}
