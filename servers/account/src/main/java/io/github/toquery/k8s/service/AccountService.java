package io.github.toquery.k8s.service;


import io.github.toquery.k8s.entity.AccountEntity;

import java.util.List;

public interface AccountService {


    List<AccountEntity> getAccounts();

    AccountEntity getAccount(int id);

    AccountEntity createAccount(AccountEntity accountEntity);

    AccountEntity updateAccount(AccountEntity accountEntity);

    void deleteAccount(int id);
}
