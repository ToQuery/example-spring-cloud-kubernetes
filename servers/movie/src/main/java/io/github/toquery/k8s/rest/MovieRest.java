package io.github.toquery.k8s.rest;

import io.github.toquery.k8s.feign.AccountFeignClient;
import io.github.toquery.k8s.dto.AccountDto;
import io.github.toquery.k8s.resilience4j.AccountServiceResilience4jClient;
import io.github.toquery.k8s.entity.MovieEntity;
import io.github.toquery.k8s.service.MovieService;
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
public class MovieRest {

    @Resource
    private AccountFeignClient accountFeignClient;

    @Resource
    private AccountServiceResilience4jClient accountServiceResilience4jClient;

    @Resource
    private MovieService movieService;

    @GetMapping
    public List<MovieEntity> getMovies() {
        return movieService.getMovies();
    }

    @GetMapping("/delay/{seconds}")
    public List<MovieEntity> getMoviesDeplay(@PathVariable int seconds) {
        return movieService.getMoviesDeplay(seconds);
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

}
