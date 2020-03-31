package io.github.toquery.k8s.service.impl;

import io.github.toquery.k8s.entity.MovieEntity;
import io.github.toquery.k8s.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImpl implements MovieService {

    private static Map<Integer, MovieEntity> movies = new HashMap<>();

    public MovieServiceImpl() {
        movies.put(1, new MovieEntity(1, "海上钢琴师", "海上钢琴师"));
        movies.put(2, new MovieEntity(2, "那些年", "那些年"));
        movies.put(3, new MovieEntity(3, "大话西游", "大话西游"));
        movies.put(4, new MovieEntity(4, "黑客帝国", "黑客帝国"));
        movies.put(5, new MovieEntity(5, "回到未来", "回到未来"));
    }

    @Override
    public List<MovieEntity> getMovies() {
        return new ArrayList<>(movies.values());
    }

    @Override
    public MovieEntity createMovie(MovieEntity movieEntity) {
        movies.put(movieEntity.getId(), movieEntity);
        return movieEntity;
    }

    @Override
    public MovieEntity getMovie(int id) {
        return movies.get(id);
    }

    @Override
    public MovieEntity updateMovie(MovieEntity movieEntity) {
        return movies.put(movieEntity.getId(), movieEntity);
    }

    @Override
    public void deleteMovie(int id) {
        movies.remove(id);
    }
}
