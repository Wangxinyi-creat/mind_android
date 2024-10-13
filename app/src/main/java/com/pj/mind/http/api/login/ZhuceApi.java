package com.pj.mind.http.api.login;

import com.hjq.http.annotation.HttpHeader;
import com.hjq.http.config.IRequestApi;

public final class ZhuceApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/register";
    }

    @HttpHeader
    private boolean isToken = false;


    private String username ;
    private String password;

    public ZhuceApi setUsername(String username) {
        this.username = username;
        return this;
    }

    public ZhuceApi setPassword(String password) {
        this.password = password;
        return this;
    }
}