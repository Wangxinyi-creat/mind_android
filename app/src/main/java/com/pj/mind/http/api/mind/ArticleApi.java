package com.pj.mind.http.api.mind;

import com.hjq.http.config.IRequestApi;

public class ArticleApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/article";
    }

    private int categoryId;
    private int userId;
    private String content;
    private int anonymous;
    private String title;

    public ArticleApi setTitle(String title) {
        this.title = title;
        return this;
    }

    public ArticleApi setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public ArticleApi setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public ArticleApi setContent(String content) {
        this.content = content;
        return this;
    }

    public ArticleApi setAnonymous(int anonymous) {
        this.anonymous = anonymous;
        return this;
    }
}
