package com.pj.mind.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.pj.common.annotation.Excel;
import com.pj.common.core.domain.BaseEntity;
import org.springframework.data.annotation.Transient;

/**
 * 测试报告对象 mind_assessment_report
 *
 * @author ruoyi
 * @date 2024-04-22
 */
public class MindAssessmentReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报告ID */
    private Long reportId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 测评表ID */
    @Excel(name = "测评表ID")
    private Long assessmentId;

    @Transient
    private String picUrl;

    /** 测评表名称 */
    @Excel(name = "测评表名称")
    private String assessmentName;

    /** 测评结果 */
    @Excel(name = "测评结果")
    private String result;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setReportId(Long reportId)
    {
        this.reportId = reportId;
    }

    public Long getReportId()
    {
        return reportId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setAssessmentId(Long assessmentId)
    {
        this.assessmentId = assessmentId;
    }

    public Long getAssessmentId()
    {
        return assessmentId;
    }
    public void setAssessmentName(String assessmentName)
    {
        this.assessmentName = assessmentName;
    }

    public String getAssessmentName()
    {
        return assessmentName;
    }
    public void setResult(String result)
    {
        this.result = result;
    }

    public String getResult()
    {
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("reportId", getReportId())
                .append("userId", getUserId())
                .append("assessmentId", getAssessmentId())
                .append("assessmentName", getAssessmentName())
                .append("result", getResult())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
