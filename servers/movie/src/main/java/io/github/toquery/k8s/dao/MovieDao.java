package io.github.toquery.k8s.dao;

import io.github.toquery.k8s.entity.MovieEntity;
import org.springframework.data.repository.CrudRepository;

/**
 *
 */
public interface MovieDao extends CrudRepository<MovieEntity, Integer> {
}
