package com.pj.mind.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.pj.common.annotation.Excel;
import com.pj.common.core.domain.BaseEntity;
import org.springframework.data.annotation.Transient;

/**
 * 咨询师预约记录对象 mind_counselor_record
 *
 * @author ruoyi
 * @date 2024-04-20
 */
public class MindCounselorRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long recordId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 咨询师ID */
    @Excel(name = "咨询师ID")
    private Long counselorId;

    @Transient
    private MindCounselor mindCounselor;

    /** 预约时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预约时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date appointmentTime;

    /** 预约地点 */
    @Excel(name = "预约地点")
    private String availableLoc;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 学院 */
    @Excel(name = "学院")
    private String college;

    /** 班级 */
    @Excel(name = "班级")
    private String collegeClass;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String phone;

    /** 预约状态 */
    @Excel(name = "预约状态")
    private Integer appointmentStatus;

    public MindCounselor getMindCounselor() {
        return mindCounselor;
    }

    public void setMindCounselor(MindCounselor mindCounselor) {
        this.mindCounselor = mindCounselor;
    }

    public void setRecordId(Long recordId)
    {
        this.recordId = recordId;
    }

    public Long getRecordId()
    {
        return recordId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setCounselorId(Long counselorId)
    {
        this.counselorId = counselorId;
    }

    public Long getCounselorId()
    {
        return counselorId;
    }
    public void setAppointmentTime(Date appointmentTime)
    {
        this.appointmentTime = appointmentTime;
    }

    public Date getAppointmentTime()
    {
        return appointmentTime;
    }
    public void setAvailableLoc(String availableLoc)
    {
        this.availableLoc = availableLoc;
    }

    public String getAvailableLoc()
    {
        return availableLoc;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setCollege(String college)
    {
        this.college = college;
    }

    public String getCollege()
    {
        return college;
    }
    public void setCollegeClass(String collegeClass)
    {
        this.collegeClass = collegeClass;
    }

    public String getCollegeClass()
    {
        return collegeClass;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setAppointmentStatus(Integer appointmentStatus)
    {
        this.appointmentStatus = appointmentStatus;
    }

    public Integer getAppointmentStatus()
    {
        return appointmentStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordId", getRecordId())
            .append("userId", getUserId())
            .append("counselorId", getCounselorId())
            .append("appointmentTime", getAppointmentTime())
            .append("availableLoc", getAvailableLoc())
            .append("name", getName())
            .append("college", getCollege())
            .append("collegeClass", getCollegeClass())
            .append("phone", getPhone())
            .append("appointmentStatus", getAppointmentStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
