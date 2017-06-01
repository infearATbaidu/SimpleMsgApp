package com.app.msg.interfaces.request;

/**
 * Created by infear on 2017/6/1.
 */
public class DeleteMsgReq {
    private Long srcId;
    private Long msgId;

    public Long getSrcId() {
        return srcId;
    }

    public void setSrcId(Long srcId) {
        this.srcId = srcId;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }
}
