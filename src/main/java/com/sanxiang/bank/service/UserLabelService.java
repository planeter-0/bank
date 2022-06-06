package com.sanxiang.bank.service;

import com.sanxiang.bank.model.entity.UserLabel;
import com.sanxiang.bank.model.vo.UserLabelVO;

public interface UserLabelService {
    void create(UserLabelVO u);
    UserLabel getByUid(Long uid);
    void update(UserLabel u);
    boolean inspect(Long uid);
}
