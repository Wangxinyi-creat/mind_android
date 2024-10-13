package com.pj.mind.http.api.mind;

import com.hjq.http.config.IRequestApi;

public class InboxListApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/inbox/listAll";
    }

    private int senderId;
    private int recipientId;

    public InboxListApi setRecipientId(int recipientId) {
        this.recipientId = recipientId;
        return this;
    }

    public InboxListApi setSenderId(int senderId) {
        this.senderId = senderId;
        return this;
    }

    public static class InboxBean {
        private int messageId;
        private int senderId;
        private int recipientId;
        private String messageContent;

        public int getMessageId() {
            return messageId;
        }

        public int getSenderId() {
            return senderId;
        }

        public int getRecipientId() {
            return recipientId;
        }

        public String getMessageContent() {
            return messageContent;
        }
    }
}
