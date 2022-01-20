package io.github.toquery.k8s;


import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.policy.TimeoutRetryPolicy;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

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

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
        TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(3))
                .build();
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindowSize(2)
                .build();

        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .timeLimiterConfig(timeLimiterConfig)
                .circuitBreakerConfig(circuitBreakerConfig)
                .build());
    }


    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> slowCustomizer() {
        return factory -> factory.configure(builder -> builder.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build()), "example-spring-cloud-kubernetes-server-movie");
    }

//    @Bean
//    public Customizer<SpringRetryCircuitBreakerFactory> defaultCustomizer() {
//        return factory -> factory.configureDefault(id -> new SpringRetryConfigBuilder(id)
//                .retryPolicy(new TimeoutRetryPolicy()).build());
//    }
//
//    @Bean
//    public Customizer<SpringRetryCircuitBreakerFactory> slowCustomizer() {
//        return factory -> factory.configure(builder -> builder.retryPolicy(new SimpleRetryPolicy(1)).build(), "slow");
//    }
}
