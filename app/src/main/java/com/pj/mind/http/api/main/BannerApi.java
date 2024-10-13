package com.pj.mind.http.api.main;

import com.hjq.http.config.IRequestApi;

public class BannerApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/banner/list";
    }


    public final static class BannerBean {

        /** 标题 */
        private String title;

        /** 图片地址 */
        private String pictureUrl;

        public String getTitle() {
            return title;
        }

        public String getPictureUrl() {
            return pictureUrl;
        }
    }
}
