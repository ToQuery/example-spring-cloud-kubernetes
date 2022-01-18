package io.github.toquery.k8s.resilience4j;

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
public class MovieServiceResilience4jClient {

    private final RestTemplate restTemplate;

    public MovieServiceResilience4jClient(@Qualifier(value = "movieRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * {service-name}.{namespace}.svc.{cluster}.local:{service-port}
     */
    @Retry(name = "getMovies", fallbackMethod = "defaultGetMovies")
    public List<MovieDto> getMovies() {
        MovieDto[] movies = restTemplate.getForObject("/movie", MovieDto[].class);
        return Arrays.asList(movies);
    }

    public List<MovieDto> defaultGetMovies(Throwable throwable) {
        throwable.printStackTrace();
        log.warn("Fallback method is called for getting movies");
        return new ArrayList<MovieDto>();
    }

    @Retry(name = "getMoviesDelay", fallbackMethod = "defaultGetMoviesDelay")
    public List<MovieDto> getMoviesDelay(int seconds) {
        MovieDto[] movies = restTemplate.getForObject("/movie/deloy" + seconds, MovieDto[].class);
        return Arrays.asList(movies);
    }

    public List<MovieDto> defaultGetMoviesDelay(Throwable throwable) {
        throwable.printStackTrace();
        log.warn("Fallback method is called for getting movies");
        return new ArrayList<MovieDto>();
    }

}
