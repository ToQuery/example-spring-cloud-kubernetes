package io.github.toquery.k8s.feign;


import io.github.toquery.k8s.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "example-spring-cloud-kubernetes-server-account", fallback = AccountFeignClientFallback.class)
public interface AccountFeignClient {


    @GetMapping("/account")
    public List<AccountDto> getAccounts();

    @GetMapping("/account/delay/{seconds}")
    public List<AccountDto> getAccountsDelay(@PathVariable int seconds);
}
