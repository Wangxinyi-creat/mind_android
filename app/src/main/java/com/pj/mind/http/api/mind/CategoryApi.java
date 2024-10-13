package com.pj.mind.http.api.mind;

import com.hjq.http.config.IRequestApi;

public class CategoryApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/category/list";
    }

    private int pid;

    public CategoryApi setPid(int pid) {
        this.pid = pid;
        return this;
    }

    public final static class CategoryBean {
        /** 主键 */
        private int id;

        /** 标题 */
        private String title;

        /** 标签 */
        private String tag;

        public String getTitle() {
            return title;
        }

        public String getTag() {
            return tag;
        }

        public int getId() {
            return id;
        }
    }
}
