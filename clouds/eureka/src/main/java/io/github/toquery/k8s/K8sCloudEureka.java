package io.github.toquery.k8s;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class K8sCloudEureka {

    public static void main(String[] args) {
        SpringApplication.run(K8sCloudEureka.class, args);
    }
}
