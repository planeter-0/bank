package com.sanxiang.bank.service.imp;

import com.sanxiang.bank.model.entity.Account;
import com.sanxiang.bank.repository.AccountRepository;
import com.sanxiang.bank.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImp implements AccountService {
    @Resource
    AccountRepository repository;

    @Override
    public boolean isExist(Long uid) {
        return repository.existsById(uid);
    }

    @Override
    public Account createAccount(Account account) {
        return repository.save(account);
    }

    @Override
    public Account getAccountByUid(Long uid) {
        return repository.getById(uid);
    }

    @Override
    public Account updateAccount(Account account) {
        return repository.save(account);
    }
}
