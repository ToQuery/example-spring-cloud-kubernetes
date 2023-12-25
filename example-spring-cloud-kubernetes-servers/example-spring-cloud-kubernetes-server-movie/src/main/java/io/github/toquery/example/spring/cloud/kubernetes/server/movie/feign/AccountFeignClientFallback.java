package io.github.toquery.example.spring.cloud.kubernetes.server.movie.feign;


import io.github.toquery.example.spring.cloud.kubernetes.server.movie.dto.AccountDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class AccountFeignClientFallback implements AccountFeignClient {


    @Override
    public List<AccountDto> getAccounts() {
        log.error("获取 AccountDto 失败。");
        return new ArrayList<>();
    }

    @Override
    public List<AccountDto> getAccountsDelay(int seconds) {
        log.error("获取 AccountDto 失败。");
        return new ArrayList<>();
    }
}
