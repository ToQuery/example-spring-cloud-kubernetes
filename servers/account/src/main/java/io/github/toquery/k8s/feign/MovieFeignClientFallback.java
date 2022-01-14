package io.github.toquery.k8s.feign;


import io.github.toquery.k8s.dto.MovieDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class MovieFeignClientFallback implements MovieFeignClient {

    @Override
    public List<MovieDto> getMovies() {
        log.error("获取 MovieDto 失败。");
        return new ArrayList<>();
    }
}
