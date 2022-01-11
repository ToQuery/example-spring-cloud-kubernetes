package io.github.toquery.k8s.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieEntity {

    private int id;

    private String name;

    private String summary;

    public MovieEntity(int id, String name, String summary) {
        this.id = id;
        this.name = name;
        this.summary = summary;
    }
}
