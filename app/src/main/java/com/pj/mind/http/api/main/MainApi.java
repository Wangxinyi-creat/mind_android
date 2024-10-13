package com.pj.mind.http.api.main;

import com.hjq.http.config.IRequestApi;

public final class MainApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/motorcycle/announceBitterArmy";
    }

    public final static class MainBean {


    }
}