package io.github.toquery.k8s.resilience4j;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.toquery.k8s.dto.MovieDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class MovieResilience4jClient {

    private final RestTemplate restTemplate;

    private final String RETRY_NAME = "example-spring-cloud-kubernetes-server-movie";
    private final String CIRCUIT_BREAKER_NAME = "example-spring-cloud-kubernetes-server-movie";

    public MovieResilience4jClient(@Qualifier(value = "movieRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     *
     */
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "defaultGetMovies")
    @Retry(name = RETRY_NAME, fallbackMethod = "defaultGetMovies")
    public List<MovieDto> getMovies() {
        MovieDto[] movies = restTemplate.getForObject("/movie", MovieDto[].class);
        return Arrays.asList(movies);
    }

    public List<MovieDto> defaultGetMovies(Throwable throwable) {
        throwable.printStackTrace();
        log.warn("Fallback method is called for getting movies");
        return new ArrayList<MovieDto>();
    }

    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "defaultGetMoviesDelay")
    @Retry(name = RETRY_NAME, fallbackMethod = "defaultGetMoviesDelay")
    public List<MovieDto> getMoviesDelay(int seconds) {
        MovieDto[] movies = restTemplate.getForObject("/movie/delay/" + seconds, MovieDto[].class);
        return Arrays.asList(movies);
    }

    public List<MovieDto> defaultGetMoviesDelay(int seconds, Throwable throwable) {
        throwable.printStackTrace();
        log.warn("Fallback method is called for getting movies");
        return new ArrayList<MovieDto>();
    }
}
