package io.github.toquery.k8s;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class K8sCloudHystrix {

    public static void main(String[] args) {
        SpringApplication.run(K8sCloudHystrix.class, args);
    }
}
