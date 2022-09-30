package io.github.toquery.k8s;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

@EnableRetry
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class K8sServerAccount {

    public static void main(String[] args) {
        SpringApplication.run(K8sServerAccount.class, args);
    }

    /**
     * to enable Ribbon support.
     */
    @Bean
    @LoadBalanced
    public RestTemplate movieRestTemplate() {
        return new RestTemplateBuilder().rootUri("http://example-spring-cloud-kubernetes-server-movie").build();
    }

//    配置或者设置bean 都可以限制
//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
//        TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
//                .timeoutDuration(Duration.ofSeconds(3))
//                .build();
//
//        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
//                .failureRateThreshold(50)
//                .waitDurationInOpenState(Duration.ofSeconds(1L))
//                .slidingWindowSize(2)
//                .build();
//
//        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
//                .timeLimiterConfig(timeLimiterConfig)
//                .circuitBreakerConfig(circuitBreakerConfig)
//                .build());
//    }
}
