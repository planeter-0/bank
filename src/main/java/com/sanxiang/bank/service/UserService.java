package com.sanxiang.bank.service;

import com.sanxiang.bank.model.entity.UserEntity;
import com.sanxiang.bank.model.param.RegisterParam;
import com.sanxiang.bank.model.vo.ResultVO;
import com.sanxiang.bank.model.vo.UserVO;

public interface UserService {
    /**
     * 注册邮箱验证
     * @param email
     */
    void verifyEmail(String email);

    /**
     * 注册
     * @param param
     * @return 用户id
     */
    Long register(RegisterParam param);

    /**
     * 获取用户验证码
     */
    String getVerifyCode(String username);
    /**
     * 更新用户
     * @param user
     * @return
     */
    UserEntity update(UserVO user);

    /**
     * 根据用户名获取用户实体
     * @param username
     * @return
     */
    UserEntity getByUsername(String username);

    UserEntity getUserById(Long id);
}
