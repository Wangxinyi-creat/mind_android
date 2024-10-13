package com.pj.mind.http.api.mind;

import com.hjq.http.config.IRequestApi;

public class InboxApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/inbox";
    }

    private int senderId;
    private int recipientId;
    private String messageContent;
    private Integer anonymous;

    public InboxApi setSenderId(int senderId) {
        this.senderId = senderId;
        return this;
    }

    public InboxApi setRecipientId(int recipientId) {
        this.recipientId = recipientId;
        return this;
    }

    public InboxApi setMessageContent(String messageContent) {
        this.messageContent = messageContent;
        return this;
    }

    public InboxApi setAnonymous(Integer anonymous) {
        this.anonymous = anonymous;
        return this;
    }
}
