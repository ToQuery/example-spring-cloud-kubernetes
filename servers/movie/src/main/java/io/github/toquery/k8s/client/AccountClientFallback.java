package io.github.toquery.k8s.client;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class AccountClientFallback implements AccountClient {


    @Override
    public List<AccountDto> getAccounts() {
        log.error("获取 AccountDto 失败。");
        return new ArrayList<>();
    }
}
