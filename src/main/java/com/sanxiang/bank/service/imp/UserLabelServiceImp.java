package com.sanxiang.bank.service.imp;

import com.sanxiang.bank.model.entity.UserLabel;
import com.sanxiang.bank.model.vo.UserLabelVO;
import com.sanxiang.bank.repository.UserLabelRepository;
import com.sanxiang.bank.service.UserLabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
@Slf4j
public class UserLabelServiceImp implements UserLabelService {
    @Resource
    private UserLabelRepository repository;

    @Override
    public void create(UserLabelVO u) {
        repository.save(u.toEntity());
    }

    @Override
    public UserLabel getByUid(Long uid) {
        return repository.getById(uid);
    }

    @Override
    public void update(UserLabel u) {
        repository.save(u);
    }

    @Override
    public boolean inspect(Long uid) {
        return true;
    }
}
