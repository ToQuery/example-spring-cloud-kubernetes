package io.github.toquery.k8s.core;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 *
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public Request.Options options() {
        return new Request.Options(3L, TimeUnit.SECONDS , 3L, TimeUnit.SECONDS, true);
    }
}
