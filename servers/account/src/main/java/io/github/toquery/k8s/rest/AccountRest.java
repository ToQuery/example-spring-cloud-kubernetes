package io.github.toquery.k8s.rest;


import io.github.toquery.k8s.dto.MovieDto;
import io.github.toquery.k8s.entity.AccountEntity;
import io.github.toquery.k8s.feign.MovieFeignClient;
import io.github.toquery.k8s.resilience4j.MovieServiceResilience4jClient;
import io.github.toquery.k8s.service.AccountService;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountRest {

    @Resource
    private MovieFeignClient movieFeignClient;

    @Resource
    private MovieServiceResilience4jClient movieServiceResilience4jClient;
//
//    @Resource
//    private CircuitBreakerFactory circuitBreakerFactory;

    @Resource
    private AccountService accountService;

    @GetMapping
    public List<AccountEntity> getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping("/delay/{seconds}")
    public List<AccountEntity> getAccountsDeplay(@PathVariable int seconds) {
        return accountService.getAccountsDeplay(seconds);
    }

    @PostMapping
    public AccountEntity createAccount(@RequestBody AccountEntity accountEntity) {
        return accountService.createAccount(accountEntity);
    }

    @GetMapping("/{id}")
    public AccountEntity getAccounts(@PathVariable int id) {
        return accountService.getAccount(id);
    }


    @PutMapping("/{id}")
    public AccountEntity updateAccount(@PathVariable int id, @RequestBody AccountEntity accountEntity) {
        return accountService.updateAccount(accountEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable int id) {
        accountService.deleteAccount(id);
    }


    @GetMapping("/movie/feign")
    public List<MovieDto> getMoviesByFeign() {
        return movieFeignClient.getMovies();
    }

    @GetMapping("/movie/feign/delay/{seconds}")
    public List<MovieDto> getMoviesDelayByFeign(@PathVariable int seconds) {
        return movieFeignClient.getMoviesDelay(seconds);
    }

    @GetMapping("/movie/resilience4j")
    public List<MovieDto> getMoviesByResilience4j() {
        return movieServiceResilience4jClient.getMovies();
    }

    @GetMapping("/movie/resilience4j/delay/{seconds}")
    public List<MovieDto> getMoviesDelayByResilience4j(@PathVariable int seconds) {
        return movieServiceResilience4jClient.getMoviesDelay(seconds);
    }
}

