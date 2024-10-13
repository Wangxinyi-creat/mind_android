package com.pj.mind.http.api.mind;

import com.hjq.http.config.IRequestApi;
import com.pj.mind.http.api.login.UserApi;

import java.util.Date;

import kotlin.jvm.Transient;

public class ArticleListApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/article/list";
    }

    private int categoryId;
    private int pageNum;
    private int pageSize = 10;

    public ArticleListApi setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public ArticleListApi setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public ArticleListApi setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public class ArticleBean{
        private int id;
        private int categoryId;
        private int userId;
        private UserApi.UserBean user;
        private String title;
        private String categoryTitle;
        private String content;
        private int isVideo;
        private String videoUrl;
        private int views;
        private int likes;
        private int comments;
        private int anonymous;
        private String createTime;

        public int getId() {
            return id;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public int getUserId() {
            return userId;
        }

        public UserApi.UserBean getUser() {
            return user;
        }

        public String getTitle() {
            return title;
        }

        public String getCategoryTitle() {
            return categoryTitle;
        }

        public String getContent() {
            return content;
        }

        public int getIsVideo() {
            return isVideo;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public int getViews() {
            return views;
        }

        public int getLikes() {
            return likes;
        }

        public int getComments() {
            return comments;
        }

        public int getAnonymous() {
            return anonymous;
        }

        public String getCreateTime() {
            return createTime;
        }
    }
}
