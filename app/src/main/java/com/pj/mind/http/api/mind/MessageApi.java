package com.pj.mind.http.api.mind;

import com.hjq.http.annotation.HttpIgnore;
import com.hjq.http.config.IRequestApi;

public class MessageApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/message";
    }


    private int senderId;
    private int recipientId;
    private int recordId;
    private String messageContent;

    public MessageApi setMessageContent(String messageContent) {
        this.messageContent = messageContent;
        return this;
    }

    public MessageApi setRecordId(int recordId) {
        this.recordId = recordId;
        return this;
    }

    public MessageApi setSenderId(int senderId) {
        this.senderId = senderId;
        return this;
    }

    public MessageApi setRecipientId(int recipientId) {
        this.recipientId = recipientId;
        return this;
    }
}
