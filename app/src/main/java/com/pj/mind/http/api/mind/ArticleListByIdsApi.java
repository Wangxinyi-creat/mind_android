package com.pj.mind.http.api.mind;

import com.hjq.http.config.IRequestApi;
import com.pj.mind.http.api.login.UserApi;

import java.util.List;

public class ArticleListByIdsApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/article/listVideoByIds";
    }

    private List<Integer> ids;

    public ArticleListByIdsApi setIds(List<Integer> ids) {
        this.ids = ids;
        return this;
    }
}
