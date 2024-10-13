package com.pj.mind.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.pj.common.annotation.Excel;
import com.pj.common.core.domain.BaseEntity;
import org.springframework.data.annotation.Transient;

/**
 * 活动预约记录对象 mind_activity_record
 *
 * @author ruoyi
 * @date 2024-04-20
 */
public class MindActivityRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long recordId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 活动ID */
    @Excel(name = "活动ID")
    private Long activityId;

    @Transient
    private MindActivity mindActivity;

    @Transient
    private Integer appointmentStatus;

    public Integer getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(Integer appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public MindActivity getMindActivity() {
        return mindActivity;
    }

    public void setMindActivity(MindActivity mindActivity) {
        this.mindActivity = mindActivity;
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
    public void setActivityId(Long activityId)
    {
        this.activityId = activityId;
    }

    public Long getActivityId()
    {
        return activityId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordId", getRecordId())
            .append("userId", getUserId())
            .append("activityId", getActivityId())
            .toString();
    }
}
