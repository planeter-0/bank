package com.sanxiang.bank.service;

import com.sanxiang.bank.model.entity.RealNameInfo;

public interface RealNameInfoService {
    /**
     * 实名信息上传
     * @param info
     * @return
     */
    RealNameInfo create(RealNameInfo info);

    /**
     * 更新实名信息，需要重新审核
     * @param info
     */
    void update(RealNameInfo info);

    /**
     * 审核通过
     * @param id
     */
    void pass(Long id);

    /**
     * 根据uid获取实名
     * @param uid
     * @return
     */
    RealNameInfo getByUid(Long uid);
    /**
     *
     */
    boolean isExist(Long uid);
}
