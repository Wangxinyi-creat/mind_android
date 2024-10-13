package com.pj.mind.http.api.mind;

import com.hjq.http.config.IRequestApi;

public class AssessmentReportApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/report";
    }

    private int assessmentId;
    private String assessmentName;
    private String result;
    private int userId;

    public AssessmentReportApi setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public AssessmentReportApi setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
        return this;
    }

    public AssessmentReportApi setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
        return this;
    }

    public AssessmentReportApi setResult(String result) {
        this.result = result;
        return this;
    }
}
