package io.github.toquery.k8s.retry;

import io.github.toquery.k8s.dto.MovieDto;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.util.List;

/**
 *
 */
public interface MovieRetryClient {

    @Retryable(value = { Exception.class }, backoff = @Backoff(delay = 3000))
    List<MovieDto> getMovies();

    @Recover
    List<MovieDto> getMoviesFallback(RuntimeException runtimeException);

    @Retryable(value = { Exception.class }, backoff = @Backoff(delay = 3000))
    List<MovieDto> getMoviesDelay(int seconds);

}
