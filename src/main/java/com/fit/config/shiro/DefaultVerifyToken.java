package com.fit.config.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @Author AIM
 * @Des 重写用户TOKEN
 * @DATE 2018/2/10
 */
public class DefaultVerifyToken extends UsernamePasswordToken {

    private String loginType;

    public DefaultVerifyToken(String username, String password, boolean rememberMe) {
        super(username, password, rememberMe);
    }

    public DefaultVerifyToken(String username, String password, boolean rememberMe, String loginType) {
        super(username, password != null ? password.toCharArray() : null, rememberMe);
        this.loginType = loginType;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}

enum LoginType {
    CUSTOM("Custom"), ADMIN("Admin");

    private String type;

    LoginType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type.toString();
    }
}