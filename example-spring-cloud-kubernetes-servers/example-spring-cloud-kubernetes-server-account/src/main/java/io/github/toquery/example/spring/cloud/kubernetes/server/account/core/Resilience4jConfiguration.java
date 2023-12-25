package io.github.toquery.k8s.core;

import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import io.github.toquery.k8s.resilience4j.MovieResilience4jClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 *
 */
@Slf4j
@Service
public class Resilience4jConfiguration {

    @Autowired
    private TimeLimiterRegistry timeLimiterRegistry;

    @PostConstruct
    void postConstruct() {
        TimeLimiter.EventPublisher eventPublisher = timeLimiterRegistry.timeLimiter(MovieResilience4jClient.BACKEND_NAME).getEventPublisher();

        eventPublisher.onSuccess(event -> log.info("onSuccess event: {}", event));
        eventPublisher.onError(event -> log.info("onError event: {}", event));
        eventPublisher.onTimeout(event -> log.info("onTimeout event: {}", event));
    }

}
