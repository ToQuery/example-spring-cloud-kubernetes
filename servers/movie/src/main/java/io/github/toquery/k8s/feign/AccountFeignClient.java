package io.github.toquery.k8s.feign;


import io.github.toquery.k8s.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "example-spring-cloud-kubernetes-server-account", fallback = AccountFeignClientFallback.class)
public interface AccountFeignClient {


    @GetMapping("/accounts")
    public List<AccountDto> getAccounts();
}
