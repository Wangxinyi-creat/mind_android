package com.pj.mind.http.api.mind;

import com.hjq.http.annotation.HttpIgnore;
import com.hjq.http.config.IRequestApi;

public class ArticleInfoApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/article/" + activityId;
    }

    @HttpIgnore
    private int activityId;

    public ArticleInfoApi setActivityId(int activityId) {
        this.activityId = activityId;
        return this;
    }
}
