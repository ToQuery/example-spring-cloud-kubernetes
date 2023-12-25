package io.github.toquery.example.spring.cloud.kubernetes.server.movie;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class ExampleSpringCloudKubernetesServerMovie {

    public static void main(String[] args) {
        SpringApplication.run(ExampleSpringCloudKubernetesServerMovie.class, args);
    }

    /**
     * to enable Ribbon support.
     */
    @Bean
    @LoadBalanced
    public RestTemplate accountRestTemplate() {
        return new RestTemplateBuilder().rootUri("http://example-spring-cloud-kubernetes-server-account").build();
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
