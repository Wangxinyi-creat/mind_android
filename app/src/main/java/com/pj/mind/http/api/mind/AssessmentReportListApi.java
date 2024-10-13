package com.pj.mind.http.api.mind;

import com.hjq.http.config.IRequestApi;

public class AssessmentReportListApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/report/list";
    }

    private int pageNum;
    private int pageSize = 10;
    private int userId;

    public AssessmentReportListApi setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public AssessmentReportListApi setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public AssessmentReportListApi setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public class AssessmentReportBean{
        private Long reportId;
        private Long userId;
        private Long assessmentId;
        private String assessmentName;
        private String result;
        private String picUrl;

        public String getPicUrl() {
            return picUrl;
        }

        public Long getReportId() {
            return reportId;
        }

        public Long getUserId() {
            return userId;
        }

        public Long getAssessmentId() {
            return assessmentId;
        }

        public String getAssessmentName() {
            return assessmentName;
        }

        public String getResult() {
            return result;
        }
    }
}
