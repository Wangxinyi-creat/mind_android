package com.pj.mind.http.api.login;

import androidx.annotation.NonNull;

import com.hjq.http.config.IRequestApi;
import com.hjq.http.config.IRequestBodyStrategy;
import com.hjq.http.config.IRequestType;
import com.hjq.http.model.RequestBodyType;

import java.io.File;

public class AvatarApi implements IRequestApi, IRequestType {

    @Override
    public String getApi() {
            return "/system/user/profile/avatar";
    }

    @NonNull
    @Override
    public IRequestBodyStrategy getBodyType() {
        // 上传文件需要使用表单的形式提交
        return RequestBodyType.FORM;
    }

    private File avatarfile;

    public AvatarApi setFile(File file) {
        this.avatarfile = file;
        return this;
    }

    public final static class UploadBean {
        private String imgUrl;

        public String getUrl() {
            return imgUrl;
        }

    }
}