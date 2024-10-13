package com.pj.mind.http.api.mind;

import com.hjq.http.config.IRequestApi;
import com.pj.mind.http.api.login.UserApi;

public class MessageListApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/message/listAll";
    }

    private int recordId;

    public MessageListApi setRecordId(int recordId) {
        this.recordId = recordId;
        return this;
    }

    public static class MessageBean {
        private int messageId;
        private int senderId;
        private int recipientId;
        private int recordId;
        private String url;
        private String messageContent;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getMessageId() {
            return messageId;
        }

        public int getSenderId() {
            return senderId;
        }

        public int getRecipientId() {
            return recipientId;
        }

        public int getRecordId() {
            return recordId;
        }

        public String getMessageContent() {
            return messageContent;
        }
    }
}
