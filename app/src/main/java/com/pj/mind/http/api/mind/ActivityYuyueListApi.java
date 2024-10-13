package com.pj.mind.http.api.mind;

import com.hjq.http.config.IRequestApi;

public class ActivityYuyueListApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/activityRecord/list";
    }

    private int userId;
    private int activityId;
    private int appointmentStatus = -1;
    private int pageNum;
    private int pageSize = 10;

    public ActivityYuyueListApi setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public ActivityYuyueListApi setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }


    public ActivityYuyueListApi setAppointmentStatus(int appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
        return this;
    }

    public ActivityYuyueListApi setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public ActivityYuyueListApi setActivityId(int activityId) {
        this.activityId = activityId;
        return this;
    }

    public class ActivityYuyueBean{
        private int recordId;
        private int userId;
        private int activityId;
        private ActivityApi.ActivityBean mindActivity;

        public int getRecordId() {
            return recordId;
        }

        public int getUserId() {
            return userId;
        }

        public int getActivityId() {
            return activityId;
        }

        public ActivityApi.ActivityBean getMindActivity() {
            return mindActivity;
        }
    }
}
