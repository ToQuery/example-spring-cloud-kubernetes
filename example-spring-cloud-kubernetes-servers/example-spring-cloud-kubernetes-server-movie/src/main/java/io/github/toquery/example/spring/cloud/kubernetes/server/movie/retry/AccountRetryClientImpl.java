package io.github.toquery.example.spring.cloud.kubernetes.server.movie.retry;

import io.github.toquery.example.spring.cloud.kubernetes.server.movie.dto.AccountDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
@Component
public class AccountRetryClientImpl implements AccountRetryClient {

    private final RestTemplate restTemplate;

    public AccountRetryClientImpl(@Qualifier(value = "accountRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<AccountDto> getAccounts() {
        AccountDto[] accounts = restTemplate.getForObject("/account", AccountDto[].class);
        return Arrays.asList(accounts);
    }

    @Override
    public List<AccountDto> getAccountsFallback(RuntimeException runtimeException) {
        return new ArrayList<AccountDto>();
    }

    @Override
    public List<AccountDto> getAccountsDelay(int seconds) {
        AccountDto[] accounts = restTemplate.getForObject("/account/delay/" + seconds, AccountDto[].class);
        return Arrays.asList(accounts);
    }

}
