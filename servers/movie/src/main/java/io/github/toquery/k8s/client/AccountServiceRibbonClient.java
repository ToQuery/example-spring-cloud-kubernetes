package io.github.toquery.k8s.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class AccountServiceRibbonClient {

    private RestTemplate restTemplate;

	public AccountServiceRibbonClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@HystrixCommand(fallbackMethod = "defaultGetAccounts")
	public List<AccountDto> getAccounts() {
		AccountDto[] accounts = restTemplate.getForObject("http://example-spring-cloud-kubernetes-server-account:8080/accounts", AccountDto[].class);
		return Arrays.asList(accounts);
	}

	public List<AccountDto> defaultGetAccounts() {
		log.warn("Fallback method is called for getting accounts");
        return new ArrayList<AccountDto>();
	}

}
