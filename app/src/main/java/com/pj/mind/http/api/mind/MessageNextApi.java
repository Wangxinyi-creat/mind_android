package com.pj.mind.http.api.mind;

import com.hjq.http.annotation.HttpIgnore;
import com.hjq.http.config.IRequestApi;

public class MessageNextApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/mind/message/next/" + recordId + "/" + messageId;
    }

    @HttpIgnore
    private int messageId;
    @HttpIgnore
    private int recordId;

    public MessageNextApi setMessageId(int messageId) {
        this.messageId = messageId;
        return this;
    }

    public MessageNextApi setRecordId(int recordId) {
        this.recordId = recordId;
        return this;
    }
}
