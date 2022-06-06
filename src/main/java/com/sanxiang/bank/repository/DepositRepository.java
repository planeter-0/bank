package com.sanxiang.bank.repository;

import com.sanxiang.bank.model.entity.Deposit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit,Long> {
    List<Deposit> getByUid(Long uid);
    Page<Deposit> findByUidAndType(Long uid, String type, Pageable pageable);
    Page<Deposit> findByUid(Long uid, Pageable pageable);
}
