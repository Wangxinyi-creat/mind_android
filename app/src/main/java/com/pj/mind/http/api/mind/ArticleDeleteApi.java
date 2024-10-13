package com.pj.mind.http.api.mind;

import com.hjq.http.annotation.HttpIgnore;
import com.hjq.http.config.IRequestApi;

public class ArticleDeleteApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/article/delete/"+id;
    }

    @HttpIgnore
    private int id;

    public ArticleDeleteApi setId(int id) {
        this.id = id;
        return this;
    }
}
