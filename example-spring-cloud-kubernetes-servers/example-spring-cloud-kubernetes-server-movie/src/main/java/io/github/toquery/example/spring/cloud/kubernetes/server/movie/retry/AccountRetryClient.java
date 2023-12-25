package io.github.toquery.example.spring.cloud.kubernetes.server.movie.retry;

import io.github.toquery.example.spring.cloud.kubernetes.server.movie.dto.AccountDto;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.util.List;

/**
 *
 */
public interface AccountRetryClient {

    @Retryable(value = { Exception.class }, backoff = @Backoff(delay = 3000))
    List<AccountDto> getAccounts();

    @Recover
    List<AccountDto> getAccountsFallback(RuntimeException runtimeException);

    @Retryable(value = { Exception.class }, backoff = @Backoff(delay = 3000))
    List<AccountDto> getAccountsDelay(int seconds);

}
