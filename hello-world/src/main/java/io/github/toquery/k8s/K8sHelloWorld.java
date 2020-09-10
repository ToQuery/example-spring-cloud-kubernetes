package io.github.toquery.k8s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;


@RefreshScope
@EnableConfigurationProperties(AppConfig.class)
@SpringBootApplication
@EnableDiscoveryClient
public class K8sHelloWorld {

    public static void main(String[] args) {
        SpringApplication.run(K8sHelloWorld.class, args);
    }
}
