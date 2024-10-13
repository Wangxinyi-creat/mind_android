package com.pj.mind.http.api.mind;

import com.hjq.http.config.IRequestApi;

public class AssessmentListApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/assessment/list";
    }

    private String assessmentName;
    private int pageNum;
    private int pageSize = 10;

    public AssessmentListApi setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
        return this;
    }

    public AssessmentListApi setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public AssessmentListApi setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public class AssessmentBean{
        private int assessmentId;
        private String assessmentName;
        private int num;
        private String picUrl;

        public String getPicUrl() {
            return picUrl;
        }

        public int getAssessmentId() {
            return assessmentId;
        }

        public String getAssessmentName() {
            return assessmentName;
        }

        public int getNum() {
            return num;
        }
    }
}
