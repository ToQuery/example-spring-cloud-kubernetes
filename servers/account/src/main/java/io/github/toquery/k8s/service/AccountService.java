package io.github.toquery.k8s.service;


import io.github.toquery.k8s.entity.AccountEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {


    private static Map<Integer, AccountEntity> accounts = new HashMap<>();

    public AccountService() {
        accounts.put(1, new AccountEntity(1, "李逍遥", "18300010001"));
        accounts.put(2, new AccountEntity(2, "赵灵儿", "18300010002"));
        accounts.put(3, new AccountEntity(3, "李月如", "18300010003"));
    }

    public List<AccountEntity> getAccounts() {
        return new ArrayList<>(accounts.values());
    }

    public List<AccountEntity> getAccountsDeplay(int seconds) {
        try {
            Thread.sleep( seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.getAccounts();
    }

    public AccountEntity getAccount(int id) {
        return accounts.get(id);
    }


    public AccountEntity createAccount(AccountEntity accountEntity) {
        accounts.put(accountEntity.getId(), accountEntity);
        return accountEntity;
    }

    public AccountEntity updateAccount(AccountEntity accountEntity) {
        accounts.put(accountEntity.getId(), accountEntity);
        return accountEntity;
    }

    public void deleteAccount(int id) {
        accounts.remove(id);
    }
}
