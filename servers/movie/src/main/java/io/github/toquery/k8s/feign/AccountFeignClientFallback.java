package io.github.toquery.k8s.feign;


import io.github.toquery.k8s.dto.AccountDto;
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
}
