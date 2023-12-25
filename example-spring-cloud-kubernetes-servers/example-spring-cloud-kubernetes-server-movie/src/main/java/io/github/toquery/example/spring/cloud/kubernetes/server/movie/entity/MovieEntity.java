package io.github.toquery.example.spring.cloud.kubernetes.server.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieEntity {

    private int id;

    private String name;

    private String summary;

}
