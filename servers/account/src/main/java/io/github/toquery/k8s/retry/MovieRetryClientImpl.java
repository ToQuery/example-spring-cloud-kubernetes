package io.github.toquery.k8s.retry;

import io.github.toquery.k8s.dto.MovieDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
@Component
public class MovieRetryClientImpl implements MovieRetryClient {

    private final RestTemplate restTemplate;

    public MovieRetryClientImpl(@Qualifier(value = "movieRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public List<MovieDto> getMovies() {
        MovieDto[] movies = restTemplate.getForObject("http://example-spring-cloud-kubernetes-server-movie/movie", MovieDto[].class);
        return Arrays.asList(movies);
    }

    @Override
    public List<MovieDto> getMoviesFallback(RuntimeException runtimeException) {
        return new ArrayList<MovieDto>();
    }

    @Override
    public List<MovieDto> getMoviesDelay(int seconds) {
        MovieDto[] movies = restTemplate.getForObject("http://example-spring-cloud-kubernetes-server-movie/movie/delay/" + seconds, MovieDto[].class);
        return Arrays.asList(movies);
    }

    @Override
    public List<MovieDto> getMoviesDelayFallback(RuntimeException runtimeException) {
        return new ArrayList<MovieDto>();
    }
}
