package com.pj.mind.http.api.mind;

import com.hjq.http.config.IRequestApi;

public class CounselorApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/counselor/list";
    }

    private String counselorName;
    private int pageNum;
    private int pageSize = 10;
    private String availableWeek;

    public CounselorApi setCounselorName(String counselorName) {
        this.counselorName = counselorName;
        return this;
    }

    public CounselorApi setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public CounselorApi setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public CounselorApi setAvailableWeek(String availableWeek) {
        this.availableWeek = availableWeek;
        return this;
    }

    public class CounselorBean{
        private int counselorId;
        private int userId;
        private String counselorName;
        private String picUrl;
        private Integer counselorLevel;
        private String education;
        private String speciality;
        private String availableTime1;
        private String availableTime2;
        private String availableWeek;

        public int getCounselorId() {
            return counselorId;
        }

        public int getUserId() {
            return userId;
        }

        public String getCounselorName() {
            return counselorName;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public Integer getCounselorLevel() {
            return counselorLevel;
        }

        public String getEducation() {
            return education;
        }

        public String getSpeciality() {
            return speciality;
        }

        public String getAvailableTime1() {
            return availableTime1;
        }

        public String getAvailableTime2() {
            return availableTime2;
        }

        public String getAvailableWeek() {
            return availableWeek;
        }
    }

}
