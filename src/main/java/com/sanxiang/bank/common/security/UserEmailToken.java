package com.sanxiang.bank.common.security;

import lombok.Data;
import org.apache.shiro.authc.HostAuthenticationToken;

@Data
public class UserEmailToken implements HostAuthenticationToken {
    private String username;
    private String token;
    private String host;


    public UserEmailToken(String username,String token, String host) {
        this.username = username;
        this.token = token;
        this.host = host;
    }

    public UserEmailToken(String username, String code) {
        this.username = username;
        this.token = code;
        this.host = null;
    }


    public String getToken() {
        return token;
    }

    public String getHost() {
        return host;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public String toString() {
        return token;
    }
}
