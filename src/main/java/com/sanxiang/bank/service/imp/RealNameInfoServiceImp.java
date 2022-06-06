package com.sanxiang.bank.service.imp;

import com.sanxiang.bank.model.entity.RealNameInfo;
import com.sanxiang.bank.repository.RealNameInfoRepository;
import com.sanxiang.bank.service.RealNameInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RealNameInfoServiceImp implements RealNameInfoService {
    @Resource
    private RealNameInfoRepository repository;
    @Override
    public RealNameInfo create(RealNameInfo info) {
        info.setPass(false);
        return repository.save(info);
    }

    @Override
    public void update(RealNameInfo info) {
        repository.save(info);
    }

    @Override
    public void pass(Long id) {
        RealNameInfo info =  repository.getById(id);
        info.setPass(true);
        repository.save(info);
    }

    @Override
    public RealNameInfo getByUid(Long uid) {
        return repository.findByUid(uid);
    }

    @Override
    public boolean isExist(Long uid) {
        return repository.existsById(uid);
    }
}
