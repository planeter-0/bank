package com.sanxiang.bank.common.security;

import com.sanxiang.bank.common.exception.ApiException;
import com.sanxiang.bank.model.entity.UserEntity;
import com.sanxiang.bank.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserEmailRealm extends AuthorizingRealm {


    // 注入用户业务
    @Resource
    UserService userService;

    // 设置Matcher
    public UserEmailRealm() {
        this.setCredentialsMatcher(new EmailCredentialsMatcher());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("————邮箱登录授权————doGetAuthorizationInfo————");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("————邮箱登录认证————doGetAuthenticationInfo————");
        UserEmailToken userEmailToken = (UserEmailToken) token;
        String codeInDb = "";
        try {
            codeInDb = (String) userService.getVerifyCode(userEmailToken.getUsername());
        } catch (Exception e) {
            throw new ApiException("验证码已失效");
        }
        // 连接数据库 查询用户数据
        UserEntity user = userService.getByUsername(userEmailToken.getPrincipal().toString());
        //因为没有密码，并且验证码在之前就验证了
        if (user == null) {
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(
                user,
                codeInDb,//Credential
                getName());
    }

    /**
     * 用来判断是否使用当前的 realm
     *
     * @param var1 传入的token
     * @return true就使用，false就不使用
     */
    @Override
    public boolean supports(AuthenticationToken var1) {
        return var1 instanceof UserEmailToken;
    }
}
