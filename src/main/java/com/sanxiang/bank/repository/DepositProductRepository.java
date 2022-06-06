package com.sanxiang.bank.repository;

import com.sanxiang.bank.model.entity.DepositProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositProductRepository extends JpaRepository<DepositProduct,Long> {
    Page<DepositProduct> findByType(Pageable pageable,String type);
}
