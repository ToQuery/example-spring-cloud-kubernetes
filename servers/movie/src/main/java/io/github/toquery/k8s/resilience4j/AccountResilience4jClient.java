package io.github.toquery.k8s.resilience4j;

import io.github.resilience4j.retry.annotation.Retry;
import io.github.toquery.k8s.dto.AccountDto;
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

	public AccountResilience4jClient(@Qualifier(value = "accountRestTemplate") RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	/**
	 * {service-name}.{namespace}.svc.{cluster}.local:{service-port}
	 */
	@Retry(name = "getAccounts", fallbackMethod = "defaultGetAccounts")
	public List<AccountDto> getAccounts() {
		AccountDto[] accounts = restTemplate.getForObject("/account", AccountDto[].class);
		return Arrays.asList(accounts);
	}

	public List<AccountDto> defaultGetAccounts(Throwable throwable) {
		throwable.printStackTrace();
		log.warn("Fallback method is called for getting accounts");
        return new ArrayList<AccountDto>();
	}
	@Retry(name = "getAccountsDelay", fallbackMethod = "defaultGetAccountsDelay")
    public List<AccountDto> getAccountsDelay(int seconds) {
		AccountDto[] accounts = restTemplate.getForObject("/account/delay/" + seconds, AccountDto[].class);
		return Arrays.asList(accounts);
    }

	public List<AccountDto> defaultGetAccountsDelay(Throwable throwable) {
		throwable.printStackTrace();
		log.warn("Fallback method is called for getting accounts");
        return new ArrayList<AccountDto>();
	}

}
