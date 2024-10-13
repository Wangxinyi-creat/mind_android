package com.pj.mind.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.pj.common.annotation.Excel;
import com.pj.common.core.domain.BaseEntity;

/**
 * 心理测评对象 mind_assessment
 *
 * @author ruoyi
 * @date 2024-04-22
 */
public class MindAssessment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 测评表ID */
    private Long assessmentId;

    /** 测评表名称 */
    @Excel(name = "测评表名称")
    private String assessmentName;

    /** 已测人数 */
    @Excel(name = "已测人数")
    private Long num;

    /** 图片地址 */
    @Excel(name = "图片地址")
    private String picUrl;

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
    public void setNum(Long num)
    {
        this.num = num;
    }

    public Long getNum()
    {
        return num;
    }
    public void setPicUrl(String picUrl)
    {
        this.picUrl = picUrl;
    }

    public String getPicUrl()
    {
        return picUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("assessmentId", getAssessmentId())
                .append("assessmentName", getAssessmentName())
                .append("num", getNum())
                .append("picUrl", getPicUrl())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
