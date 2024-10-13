package com.pj.mind.http.api.login;

import com.hjq.http.config.IRequestApi;

public class UserApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/system/user/profile";
    }

    public static class UserBean {
        private int userId;
        private String phonenumber;
        private String nickName;
        private String sex;
        private String avatar;

        public String getNickName() {
            return nickName;
        }

        public int getUserId() {
            return userId;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public String getSex() {
            return sex;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
