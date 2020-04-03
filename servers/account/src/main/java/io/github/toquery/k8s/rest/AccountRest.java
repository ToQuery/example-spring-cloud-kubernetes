package io.github.toquery.k8s.rest;


import io.github.toquery.k8s.client.MovieClient;
import io.github.toquery.k8s.client.MovieDto;
import io.github.toquery.k8s.client.MovieServiceRibbonClient;
import io.github.toquery.k8s.entity.AccountEntity;
import io.github.toquery.k8s.service.AccountService;
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

@RestController
@RequestMapping("/accounts")
public class AccountRest {

    @Resource
    private MovieClient movieClient;

    @Resource
    private MovieServiceRibbonClient movieServiceRibbonClient;

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
        return movieClient.getMovies();
    }

    @GetMapping("/movies/ribbon")
    public List<MovieDto> getMoviesByRibbon() {
        return movieServiceRibbonClient.getMovies();
    }

}
