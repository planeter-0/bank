package com.sanxiang.bank.service.imp;

import com.sanxiang.bank.common.exception.ApiException;
import com.sanxiang.bank.model.entity.Deposit;
import com.sanxiang.bank.model.entity.DepositProduct;
import com.sanxiang.bank.repository.DepositProductRepository;
import com.sanxiang.bank.service.DepositProductService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepositProductServiceImp implements DepositProductService {
    @Resource
    DepositProductRepository repository;

    @Override
    public void create(DepositProduct d) {
        repository.save(d);
    }

    @Override
    public DepositProduct getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public DepositProduct lock(Long productId, int amount) {
        DepositProduct d = repository.getById(productId);
        if(d.getStock() - d.getLocking() < amount){
            throw new ApiException("超出未锁定的库存");
        }
        d.setLocking(d.getLocking()+amount);
        return repository.save(d);
    }

    @Override
    public DepositProduct unlock(Long productId, int amount) {
        DepositProduct d = repository.getById(productId);
        if(d.getLocking()< amount){
            throw new ApiException("超出已锁定的库存");
        }
        d.setStock(d.getStock() - amount);
        d.setLocking(d.getLocking() - amount);
        return repository.save(d);
    }

    @Override
    public Page<DepositProduct> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    @Override
    public Page<DepositProduct> findByType(String type, PageRequest pageRequest) {
        return repository.findByType(pageRequest,type);
    }


    @Override
    public void update(DepositProduct d) {
        repository.save(d);
    }
}
