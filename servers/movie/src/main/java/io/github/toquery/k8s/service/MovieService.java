package io.github.toquery.k8s.service;

import io.github.toquery.k8s.client.AccountClient;
import io.github.toquery.k8s.client.AccountDto;
import io.github.toquery.k8s.entity.MovieEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public interface MovieService {

    List<MovieEntity> getMovies();

    MovieEntity createMovie(MovieEntity movieEntity);

    MovieEntity getMovie(int id);

    MovieEntity updateMovie(MovieEntity movieEntity);

    void deleteMovie(int id);
}
