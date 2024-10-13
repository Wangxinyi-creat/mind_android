package com.pj.mind.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.pj.common.annotation.Excel;
import com.pj.common.core.domain.BaseEntity;

/**
 * 消息对象 mind_message
 * 
 * @author ruoyi
 * @date 2024-04-21
 */
public class MindMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 消息ID */
    private Long messageId;

    /** 发送者ID */
    @Excel(name = "发送者ID")
    private Long senderId;

    /** 接收者ID */
    @Excel(name = "接收者ID")
    private Long recipientId;

    /** 预约ID */
    @Excel(name = "预约ID")
    private Long recordId;

    /** 信件内容 */
    @Excel(name = "信件内容")
    private String messageContent;

    public void setMessageId(Long messageId) 
    {
        this.messageId = messageId;
    }

    public Long getMessageId() 
    {
        return messageId;
    }
    public void setSenderId(Long senderId) 
    {
        this.senderId = senderId;
    }

    public Long getSenderId() 
    {
        return senderId;
    }
    public void setRecipientId(Long recipientId) 
    {
        this.recipientId = recipientId;
    }

    public Long getRecipientId() 
    {
        return recipientId;
    }
    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }
    public void setMessageContent(String messageContent) 
    {
        this.messageContent = messageContent;
    }

    public String getMessageContent() 
    {
        return messageContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("messageId", getMessageId())
            .append("senderId", getSenderId())
            .append("recipientId", getRecipientId())
            .append("recordId", getRecordId())
            .append("messageContent", getMessageContent())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
