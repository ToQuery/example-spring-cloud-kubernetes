package io.github.toquery.k8s.feign;


import io.github.toquery.k8s.dto.MovieDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "example-spring-cloud-kubernetes-server-movie", fallback = MovieFeignClientFallback.class)
public interface MovieFeignClient {
    @GetMapping("/movie")
    List<MovieDto> getMovies();

    @GetMapping("/movie/delay/{seconds}")
    List<MovieDto> getMoviesDelay(@PathVariable int seconds);
}
