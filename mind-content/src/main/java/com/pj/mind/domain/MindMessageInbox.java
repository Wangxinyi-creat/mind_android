package com.pj.mind.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.pj.common.annotation.Excel;
import com.pj.common.core.domain.BaseEntity;

/**
 * 心晴信箱对象 mind_message_inbox
 * 
 * @author ruoyi
 * @date 2024-04-21
 */
public class MindMessageInbox extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 信件ID */
    private Long messageId;

    /** 发送者ID */
    @Excel(name = "发送者ID")
    private Long senderId;

    /** 接收者ID */
    @Excel(name = "接收者ID")
    private Long recipientId;

    /** 信件内容 */
    @Excel(name = "信件内容")
    private String messageContent;

    /** 是否回复 */
    @Excel(name = "是否回复")
    private Integer reply;

    /** 是否匿名 */
    @Excel(name = "是否匿名")
    private Integer anonymous;

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
    public void setMessageContent(String messageContent) 
    {
        this.messageContent = messageContent;
    }

    public String getMessageContent() 
    {
        return messageContent;
    }
    public void setReply(Integer reply) 
    {
        this.reply = reply;
    }

    public Integer getReply() 
    {
        return reply;
    }
    public void setAnonymous(Integer anonymous) 
    {
        this.anonymous = anonymous;
    }

    public Integer getAnonymous() 
    {
        return anonymous;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("messageId", getMessageId())
            .append("senderId", getSenderId())
            .append("recipientId", getRecipientId())
            .append("messageContent", getMessageContent())
            .append("reply", getReply())
            .append("anonymous", getAnonymous())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
