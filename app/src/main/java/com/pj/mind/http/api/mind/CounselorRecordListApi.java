package com.pj.mind.http.api.mind;

import com.hjq.http.config.IRequestApi;

public class CounselorRecordListApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/counselorRecord/list";
    }

    private int userId;
    private int appointmentStatus = -1;
    private int pageNum;
    private int pageSize = 10;

    public CounselorRecordListApi setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public CounselorRecordListApi setAppointmentStatus(int appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
        return this;
    }

    public CounselorRecordListApi setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public CounselorRecordListApi setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public class CounselorRecordBean {
        private int recordId;
        private int userId;
        private int counselorId;
        private CounselorApi.CounselorBean mindCounselor;
        private int appointmentStatus;
        private String name;
        private String college;
        private String collegeClass;
        private String phone;

        public int getAppointmentStatus() {
            return appointmentStatus;
        }

        public String getName() {
            return name;
        }

        public String getCollege() {
            return college;
        }

        public String getCollegeClass() {
            return collegeClass;
        }

        public String getPhone() {
            return phone;
        }

        public int getRecordId() {
            return recordId;
        }

        public int getUserId() {
            return userId;
        }

        public int getCounselorId() {
            return counselorId;
        }

        public CounselorApi.CounselorBean getMindCounselor() {
            return mindCounselor;
        }
    }
}
