package io.github.toquery.k8s.client;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class MovieClientFallback implements MovieClient {

    @Override
    public List<MovieDto> getMovies() {
        log.error("获取 MovieDto 失败。");
        return new ArrayList<>();
    }
}
