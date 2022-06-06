package com.sanxiang.bank.controller.api;

import com.sanxiang.bank.common.exception.ApiException;
import com.sanxiang.bank.model.entity.Account;
import com.sanxiang.bank.model.entity.RealNameInfo;
import com.sanxiang.bank.service.AccountService;

import com.sanxiang.bank.service.RealNameInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
public class AccountController {
    @Resource
    AccountService service;
    @Resource
    RealNameInfoService infoService;

    @PostMapping("/user/account/create")
    public Account createAccount(@RequestParam Long uid) {
        RealNameInfo info = infoService.getByUid(uid);
        if (info == null) {
            throw new ApiException("没有实名信息");
        } else if (info.isPass()) {
            throw new ApiException("未通过实名认证");
        } else if (service.isExist(uid)) {
            throw new ApiException("已有账户，请勿重复创建");
        } else {
            log.info("UID:[{}]的账户创建成功", uid);
            return service.createAccount(new Account(uid));
        }
    }

    @GetMapping("/user/account/{uid}")
    public Account getByUid(@PathVariable Long uid) {
        Account account = service.getAccountByUid(uid);
        if (account != null){
            log.info("账户信息:[{}]",account.toString());
        }
        return account;
    }

    @PutMapping("/user/account/update")
    public Account getByUid(@RequestBody Account account) {
        Account newAccount = service.createAccount(account);
        if (newAccount != null) {
            log.info("UID:[{}]的账户更新成功", account.getUid());
            return newAccount;
        } else {
            log.error("UID:[{}]的账户更新失败", account.getUid());
            return null;
        }
    }
}
