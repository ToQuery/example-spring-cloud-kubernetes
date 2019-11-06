package io.github.toquery.k8s;


import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableAdminServer
@SpringBootApplication
@EnableDiscoveryClient
public class K8sCloudAdmin {

    public static void main(String[] args) {
        SpringApplication.run(K8sCloudAdmin.class, args);
    }
}
