package com.pj.mind.http.api.mind;

import com.hjq.http.config.IRequestApi;

public class CommentApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/comment";
    }

    private int articleId;
    private int userId;
    private String content;

    public CommentApi setArticleId(int articleId) {
        this.articleId = articleId;
        return this;
    }

    public CommentApi setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public CommentApi setContent(String content) {
        this.content = content;
        return this;
    }
}
