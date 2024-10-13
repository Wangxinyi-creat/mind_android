package com.pj.mind.http.api.mind;

import com.hjq.http.config.IRequestApi;

public class CounselorRecordApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/counselorRecord";
    }

    private int counselorId;
    private int userId;
    private String name;
    private String college;
    private String collegeClass;
    private String phone;

    public CounselorRecordApi setCounselorId(int counselorId) {
        this.counselorId = counselorId;
        return this;
    }

    public CounselorRecordApi setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public CounselorRecordApi setName(String name) {
        this.name = name;
        return this;
    }

    public CounselorRecordApi setCollege(String college) {
        this.college = college;
        return this;
    }

    public CounselorRecordApi setCollegeClass(String collegeClass) {
        this.collegeClass = collegeClass;
        return this;
    }

    public CounselorRecordApi setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
