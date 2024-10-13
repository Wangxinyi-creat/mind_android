package com.pj.mind.http.api.mind;

import com.hjq.http.config.IRequestApi;

public class ActivityYuyueApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/activityRecord";
    }

    private int userId;
    private int activityId;

    public ActivityYuyueApi setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public ActivityYuyueApi setActivityId(int activityId) {
        this.activityId = activityId;
        return this;
    }
}
