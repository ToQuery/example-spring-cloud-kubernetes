package io.github.toquery.example.spring.cloud.kubernetes.server.movie.rest;

import io.github.toquery.example.spring.cloud.kubernetes.server.movie.feign.AccountFeignClient;
import io.github.toquery.example.spring.cloud.kubernetes.server.movie.dto.AccountDto;
import io.github.toquery.example.spring.cloud.kubernetes.server.movie.resilience4j.AccountResilience4jClient;
import io.github.toquery.example.spring.cloud.kubernetes.server.movie.entity.MovieEntity;
import io.github.toquery.example.spring.cloud.kubernetes.server.movie.retry.AccountRetryClient;
import io.github.toquery.example.spring.cloud.kubernetes.server.movie.service.MovieService;
import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/movie")
@RestController
@Timed(value = "example-spring-cloud-kubernetes-movie", description = "Movie 请求统计")
public class MovieRest {

    @Resource
    private AccountFeignClient accountFeignClient;

    @Resource
    private AccountRetryClient accountRetryClient;

    @Resource
    private AccountResilience4jClient accountServiceResilience4jClient;

    @Resource
    private MovieService movieService;

    @GetMapping
    public List<MovieEntity> getMovies() {
        return movieService.getMovies();
    }

    @GetMapping("/delay/{seconds}")
    public List<MovieEntity> getMoviesDelay(@PathVariable int seconds) {
        return movieService.getMoviesDelay(seconds);
    }

    @PostMapping
    public MovieEntity createMovie(@RequestBody MovieEntity movieEntity) {
        return movieService.createMovie(movieEntity);
    }

    @GetMapping("/{id}")
    public MovieEntity getMovie(@PathVariable int id) {
        return movieService.getMovie(id);
    }

    @PutMapping("/{id}")
    public MovieEntity updateMovie(@PathVariable int id, @RequestBody MovieEntity movieEntity) {
        return movieService.updateMovie(movieEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable int id) {
        movieService.deleteMovie(id);
    }

    @GetMapping("/account/feign")
    public List<AccountDto> getAccountsByFeign() {
        return accountFeignClient.getAccounts();
    }

    @GetMapping("/account/feign/delay/{seconds}")
    public List<AccountDto> getAccountsDelayByFeign(@PathVariable int seconds) {
        return accountFeignClient.getAccountsDelay(seconds);
    }

    @GetMapping("/account/resilience4j")
    public List<AccountDto> getAccountsByResilience4j() {
        return accountServiceResilience4jClient.getAccounts();
    }

    @GetMapping("/account/resilience4j/delay/{seconds}")
    public List<AccountDto> getAccountsDelayByResilience4j(@PathVariable int seconds) {
        return accountServiceResilience4jClient.getAccountsDelay(seconds);
    }

    @GetMapping("/account/retry")
    public List<AccountDto> getAccountsByRetry() {
        return accountRetryClient.getAccounts();
    }

    @GetMapping("/account/retry/delay/{seconds}")
    public List<AccountDto> getAccountsDelayByRetry(@PathVariable int seconds) {
        return accountRetryClient.getAccountsDelay(seconds);
    }

}
