package com.pj.mind.http.api.mind;

import com.hjq.http.config.IRequestApi;

import java.util.Date;

public class ActivityApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/activity/list";
    }

    private int appointmentStatus;
    private int pageNum;
    private int pageSize = 10;

    public ActivityApi setAppointmentStatus(int appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
        return this;
    }

    public ActivityApi setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public ActivityApi setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public final static class ActivityBean {
        private int activityId;
        private String activityName;
        private String activityTime;
        private String location;
        private String details;
        private Integer appointmentStatus;
        private Integer capacity;
        private Integer capacityNow;

        public int getActivityId() {
            return activityId;
        }

        public String getActivityName() {
            return activityName;
        }

        public String getActivityTime() {
            return activityTime;
        }

        public String getLocation() {
            return location;
        }

        public String getDetails() {
            return details;
        }

        public Integer getAppointmentStatus() {
            return appointmentStatus;
        }

        public Integer getCapacity() {
            return capacity;
        }

        public Integer getCapacityNow() {
            return capacityNow;
        }
    }
}
