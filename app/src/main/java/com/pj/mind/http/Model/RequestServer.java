package com.pj.mind.http.Model;


import androidx.annotation.NonNull;

import com.hjq.http.config.IRequestBodyStrategy;
import com.hjq.http.config.IRequestServer;
import com.hjq.http.model.CacheMode;
import com.hjq.http.model.RequestBodyType;
import com.pj.mind.AppContract;

public class RequestServer implements IRequestServer {

    @NonNull
    @Override
    public String getHost() {
        return AppContract.BASE_URL;
    }

    @NonNull
    @Override
    public IRequestBodyStrategy getBodyType() {
        return RequestBodyType.JSON;
    }

    @NonNull
    @Override
    public CacheMode getCacheMode() {
        // 只在请求失败才去读缓存
        return CacheMode.NO_CACHE;
    }
}
