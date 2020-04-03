package io.github.toquery.k8s.rest;

import io.github.toquery.k8s.client.AccountClient;
import io.github.toquery.k8s.client.AccountDto;
import io.github.toquery.k8s.client.AccountServiceRibbonClient;
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

@RestController
@RequestMapping("/movies")
public class MovieRest {

    @Resource
    private AccountClient accountClient;

    @Resource
    private AccountServiceRibbonClient accountServiceRibbonClient;

    @Resource
    private MovieService movieService;

    @GetMapping
    public List<MovieEntity> getMovies() {
        return movieService.getMovies();
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

    @GetMapping("/accounts/feign")
    public List<AccountDto> getAccountsByFeign() {
        return accountClient.getAccounts();
    }

    @GetMapping("/accounts/ribbon")
    public List<AccountDto> getAccountsByRibbon() {
        return accountServiceRibbonClient.getAccounts();
    }

}
