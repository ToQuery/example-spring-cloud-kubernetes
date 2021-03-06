package io.github.toquery.k8s.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "example-spring-cloud-kubernetes-server-movie", fallback = MovieClientFallback.class)
public interface MovieClient {
    @GetMapping("/movies")
    List<MovieDto> getMovies();
}
