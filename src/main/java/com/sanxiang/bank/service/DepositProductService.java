package com.sanxiang.bank.service;

import com.sanxiang.bank.model.entity.DepositProduct;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepositProductService {
    void create(DepositProduct d);
    DepositProduct getById(Long id);
    DepositProduct lock(Long productId, int amount);
    Page<DepositProduct> findByType(String type, PageRequest pageRequest);
    void update(DepositProduct d);
    DepositProduct unlock(Long productId, int amount);
    Page<DepositProduct> findAll(PageRequest pageRequest);

}
