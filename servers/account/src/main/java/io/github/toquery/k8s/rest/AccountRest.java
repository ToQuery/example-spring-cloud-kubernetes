package io.github.toquery.k8s.rest;


import io.github.toquery.k8s.feign.MovieFeignClient;
import io.github.toquery.k8s.dto.MovieDto;
import io.github.toquery.k8s.resilience4j.MovieServiceResilience4jClient;
import io.github.toquery.k8s.entity.AccountEntity;
import io.github.toquery.k8s.service.AccountService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class AccountRest {

    @Resource
    private MovieFeignClient movieFeignClient;

    @Resource
    private MovieServiceResilience4jClient movieServiceResilience4jClient;

    @Resource
    private AccountService accountService;

    @GetMapping
    public List<AccountEntity> getAccounts() {
        return accountService.getAccounts();
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

    @GetMapping("/movies/feign")
    public List<MovieDto> getMoviesByFeign() {
        return movieFeignClient.getMovies();
    }

    @GetMapping("/movies/resilience4j")
    public List<MovieDto> getMoviesByRibbon() {
        return movieServiceResilience4jClient.getMovies();
    }

}
