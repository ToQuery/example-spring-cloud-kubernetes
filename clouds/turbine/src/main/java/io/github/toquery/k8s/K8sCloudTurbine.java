package io.github.toquery.k8s;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

@SpringBootApplication
@EnableTurbineStream
public class K8sCloudTurbine {

    public static void main(String[] args) {
        SpringApplication.run(K8sCloudTurbine.class, args);
    }
}
