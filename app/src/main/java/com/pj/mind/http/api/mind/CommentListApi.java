package com.pj.mind.http.api.mind;

import com.hjq.http.annotation.HttpIgnore;
import com.hjq.http.config.IRequestApi;
import com.pj.mind.http.api.login.UserApi;

public class CommentListApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/comment/listAll";
    }

    private int articleId;

    public CommentListApi setArticleId(int articleId) {
        this.articleId = articleId;
        return this;
    }

    public static class CommentBean {
        private int id;
        private int articleId;
        private int userId;
        private UserApi.UserBean user;
        private String content;
        private String createTime;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getArticleId() {
            return articleId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public UserApi.UserBean getUser() {
            return user;
        }

        public void setUser(UserApi.UserBean user) {
            this.user = user;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
