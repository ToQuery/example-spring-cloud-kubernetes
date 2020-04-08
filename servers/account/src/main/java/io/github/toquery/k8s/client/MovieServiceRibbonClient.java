package io.github.toquery.k8s.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class MovieServiceRibbonClient {

    private RestTemplate restTemplate;

	public MovieServiceRibbonClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * {service-name}.{namespace}.svc.{cluster}.local:{service-port}
	 */
	@HystrixCommand(fallbackMethod = "defaultGetMovies")
	public List<MovieDto> getMovies() {
		MovieDto[] movies = restTemplate.getForObject("http://example-spring-cloud-kubernetes-server-movie.example-spring-cloud-kubernetes.svc:8080/movies", MovieDto[].class);
		return Arrays.asList(movies);
	}

	public List<MovieDto> defaultGetMovies() {
		log.warn("Fallback method is called for getting movies");
        return new ArrayList<MovieDto>();
	}

}
