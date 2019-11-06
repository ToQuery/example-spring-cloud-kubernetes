package io.github.toquery.k8s.rest;


import io.github.toquery.k8s.client.MovieClient;
import io.github.toquery.k8s.client.MovieDto;
import io.github.toquery.k8s.entity.AccountEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountRest {

    @Resource
    private MovieClient movieClient;

    private static List<AccountEntity> accounts = new ArrayList<>();

    public AccountRest() {
        for (int i = 1; i < 10; i++) {
            accounts.add(new AccountEntity(i, " account " + i));
        }
    }

    @GetMapping
    public List<AccountEntity> getAccounts() {
        return accounts;
    }


    @PostMapping
    public AccountEntity saveAccount(@RequestBody AccountEntity accountEntity) {
        accounts.add(accountEntity);
        return accountEntity;
    }


    @GetMapping("/movies")
    public List<MovieDto> getMovies() {
        return movieClient.getMovies();
    }

}
