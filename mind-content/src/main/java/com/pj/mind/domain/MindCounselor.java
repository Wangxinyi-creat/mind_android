package com.pj.mind.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.pj.common.annotation.Excel;
import com.pj.common.core.domain.BaseEntity;

/**
 * 咨询师对象 mind_counselor
 *
 * @author ruoyi
 * @date 2024-04-20
 */
public class MindCounselor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 咨询师ID */
    private Long counselorId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 咨询师姓名 */
    @Excel(name = "咨询师姓名")
    private String counselorName;
    @Excel(name = "图片地址")
    private String picUrl;

    /** 咨询师等级 */
    @Excel(name = "咨询师等级")
    private Integer counselorLevel;

    /** 资格证 */
    @Excel(name = "资格证")
    private String education;

    /** 擅长 */
    @Excel(name = "擅长")
    private String speciality;

    /** 可预约时间 */
    @Excel(name = "可预约时间")
    private String availableTime1;
    @Excel(name = "可预约时间")
    private String availableTime2;

    /** 可预约星期 */
    @Excel(name = "可预约星期")
    private String availableWeek;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setCounselorId(Long counselorId)
    {
        this.counselorId = counselorId;
    }

    public Long getCounselorId()
    {
        return counselorId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setCounselorName(String counselorName)
    {
        this.counselorName = counselorName;
    }

    public String getCounselorName()
    {
        return counselorName;
    }
    public void setCounselorLevel(Integer counselorLevel)
    {
        this.counselorLevel = counselorLevel;
    }

    public Integer getCounselorLevel()
    {
        return counselorLevel;
    }
    public void setEducation(String education)
    {
        this.education = education;
    }

    public String getEducation()
    {
        return education;
    }
    public void setSpeciality(String speciality)
    {
        this.speciality = speciality;
    }

    public String getSpeciality()
    {
        return speciality;
    }

    public String getAvailableWeek()
    {
        return availableWeek;
    }

    public String getAvailableTime1() {
        return availableTime1;
    }

    public void setAvailableTime1(String availableTime1) {
        this.availableTime1 = availableTime1;
    }

    public String getAvailableTime2() {
        return availableTime2;
    }

    public void setAvailableTime2(String availableTime2) {
        this.availableTime2 = availableTime2;
    }

    public void setAvailableWeek(String availableWeek) {
        this.availableWeek = availableWeek;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("counselorId", getCounselorId())
            .append("userId", getUserId())
            .append("counselorName", getCounselorName())
            .append("counselorLevel", getCounselorLevel())
            .append("education", getEducation())
            .append("speciality", getSpeciality())
            .append("availableWeek", getAvailableWeek())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
