package io.github.toquery.k8s.rest;

import io.github.toquery.k8s.client.AccountClient;
import io.github.toquery.k8s.client.AccountDto;
import io.github.toquery.k8s.entity.MovieEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieRest {
    @Resource
    private AccountClient accountClient;

    private static List<MovieEntity> movies = new ArrayList<>();

    public MovieRest() {
        for (int i = 1; i < 10; i++) {
            movies.add(new MovieEntity(i, " movie " + i));
        }
    }

    @GetMapping
    public List<MovieEntity> getMovies() {
        return movies;
    }


    @PostMapping
    public MovieEntity saveMovie(@RequestBody MovieEntity movieEntity) {
        movies.add(movieEntity);
        return movieEntity;
    }


    @GetMapping("/accounts")
    public List<AccountDto> getAccounts() {
        return accountClient.getAccounts();
    }
}
