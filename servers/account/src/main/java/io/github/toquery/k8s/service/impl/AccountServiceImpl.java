package io.github.toquery.k8s.service.impl;


import io.github.toquery.k8s.entity.AccountEntity;
import io.github.toquery.k8s.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {


    private static Map<Integer, AccountEntity> accounts = new HashMap<>();

    public AccountServiceImpl() {
        accounts.put(1, new AccountEntity(1, "李逍遥", "18300010001"));
        accounts.put(2, new AccountEntity(2, "赵灵儿", "18300010002"));
        accounts.put(3, new AccountEntity(3, "李月如", "18300010003"));
    }

    @GetMapping
    public List<AccountEntity> getAccounts() {
        return new ArrayList<>(accounts.values());
    }

    @Override
    public AccountEntity getAccount(int id) {
        return accounts.get(id);
    }

    @Override
    public AccountEntity createAccount(AccountEntity accountEntity) {
        accounts.put(accountEntity.getId(), accountEntity);
        return accountEntity;
    }

    @Override
    public AccountEntity updateAccount(AccountEntity accountEntity) {
        accounts.put(accountEntity.getId(), accountEntity);
        return accountEntity;
    }

    @Override
    public void deleteAccount(int id) {
        accounts.remove(id);
    }

}
