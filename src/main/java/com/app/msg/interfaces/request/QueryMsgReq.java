package com.app.msg.interfaces.request;

/**
 * Created by infear on 2017/5/30.
 */
public class QueryMsgReq {
    private Long srcId;
    private Long destId;

    public Long getSrcId() {
        return srcId;
    }

    public void setSrcId(Long srcId) {
        this.srcId = srcId;
    }

    public Long getDestId() {
        return destId;
    }

    public void setDestId(Long destId) {
        this.destId = destId;
    }
}
