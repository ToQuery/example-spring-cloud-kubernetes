package io.github.toquery.example.spring.cloud.kubernetes.server.movie.resilience4j;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.toquery.example.spring.cloud.kubernetes.server.movie.dto.AccountDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class AccountResilience4jClient {

    private final RestTemplate restTemplate;

	private final String RETRY_NAME = "example-spring-cloud-kubernetes-server-account";
	private final String CIRCUIT_BREAKER_NAME = "example-spring-cloud-kubernetes-server-account";


	public AccountResilience4jClient(@Qualifier(value = "accountRestTemplate") RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 *
	 */
	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "defaultGetAccounts")
	@Retry(name = RETRY_NAME, fallbackMethod = "defaultGetAccounts")
	public List<AccountDto> getAccounts() {
		AccountDto[] accounts = restTemplate.getForObject("/account", AccountDto[].class);
		return Arrays.asList(accounts);
	}

	public List<AccountDto> defaultGetAccounts(Throwable throwable) {
		throwable.printStackTrace();
		log.warn("Fallback method is called for getting accounts");
		return new ArrayList<AccountDto>();
	}

	@CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "defaultGetAccountsDelay")
	@Retry(name = RETRY_NAME, fallbackMethod = "defaultGetAccountsDelay")
    public List<AccountDto> getAccountsDelay(int seconds) {
		AccountDto[] accounts = restTemplate.getForObject("/account/delay/" + seconds, AccountDto[].class);
		return Arrays.asList(accounts);
    }

	public List<AccountDto> defaultGetAccountsDelay(int seconds, Throwable throwable) {
		throwable.printStackTrace();
		log.warn("Fallback method is called for getting accounts");
		return new ArrayList<AccountDto>();
	}
}
