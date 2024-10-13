package com.pj.mind.http.api.login;

import com.hjq.http.annotation.HttpHeader;
import com.hjq.http.config.IRequestApi;

public final class LoginApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/login";
    }

    @HttpHeader
    private boolean isToken = false;


    private String username ;
    private String password;

    public LoginApi setUsername(String username) {
        this.username = username;
        return this;
    }

    public LoginApi setPassword(String password) {
        this.password = password;
        return this;
    }
}