package com.sanxiang.bank.repository;

import com.sanxiang.bank.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
