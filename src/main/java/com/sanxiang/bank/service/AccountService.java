package com.sanxiang.bank.service;

import com.sanxiang.bank.model.entity.Account;

public interface AccountService {
    boolean isExist(Long uid);

    Account createAccount(Account account);

    Account getAccountByUid(Long uid);

    Account updateAccount(Account account);
}
