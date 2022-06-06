package com.sanxiang.bank.common.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sanxiang.bank.model.entity.UserEntity;
import com.sanxiang.bank.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@Slf4j
@Component
public class EmailCredentialsMatcher implements CredentialsMatcher {


    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        String token = (String) authenticationToken.getCredentials();

        UserEntity user = (UserEntity) authenticationInfo.getPrincipals().getPrimaryPrincipal();
        try {
            if (token.equals(authenticationInfo.getCredentials())) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            log.warn("Signature resulted invalid");
        }
        return false;
    }
}
