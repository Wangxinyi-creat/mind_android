package com.pj.mind.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.pj.common.annotation.Excel;
import com.pj.common.core.domain.BaseEntity;

/**
 * 校园心理活动对象 mind_activity
 *
 * @author ruoyi
 * @date 2024-04-20
 */
public class MindActivity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 活动ID */
    private Long activityId;

    /** 活动名称 */
    @Excel(name = "活动名称")
    private String activityName;

    /** 时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd  HH:mm:ss")
    private Date activityTime;

    /** 地点 */
    @Excel(name = "地点")
    private String location;

    /** 详情 */
    @Excel(name = "详情")
    private String details;

    /** 预约状态 */
    @Excel(name = "预约状态")
    private Integer appointmentStatus;

    /** 人数限制 */
    @Excel(name = "人数限制")
    private Integer capacity;

    /** 当前人数 */
    @Excel(name = "当前人数")
    private Integer capacityNow;

    public void setActivityId(Long activityId)
    {
        this.activityId = activityId;
    }

    public Long getActivityId()
    {
        return activityId;
    }
    public void setActivityName(String activityName)
    {
        this.activityName = activityName;
    }

    public String getActivityName()
    {
        return activityName;
    }
    public void setActivityTime(Date activityTime)
    {
        this.activityTime = activityTime;
    }

    public Date getActivityTime()
    {
        return activityTime;
    }
    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getLocation()
    {
        return location;
    }
    public void setDetails(String details)
    {
        this.details = details;
    }

    public String getDetails()
    {
        return details;
    }
    public void setAppointmentStatus(Integer appointmentStatus)
    {
        this.appointmentStatus = appointmentStatus;
    }

    public Integer getAppointmentStatus()
    {
        return appointmentStatus;
    }
    public void setCapacity(Integer capacity)
    {
        this.capacity = capacity;
    }

    public Integer getCapacity()
    {
        return capacity;
    }
    public void setCapacityNow(Integer capacityNow)
    {
        this.capacityNow = capacityNow;
    }

    public Integer getCapacityNow()
    {
        return capacityNow;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("activityId", getActivityId())
            .append("activityName", getActivityName())
            .append("activityTime", getActivityTime())
            .append("location", getLocation())
            .append("details", getDetails())
            .append("appointmentStatus", getAppointmentStatus())
            .append("capacity", getCapacity())
            .append("capacityNow", getCapacityNow())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
