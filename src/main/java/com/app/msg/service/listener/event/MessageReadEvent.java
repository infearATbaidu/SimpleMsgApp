package com.app.msg.service.listener.event;

/**
 * Created by infear on 2017/6/2.
 */
public class MessageReadEvent {
    private Long srcId;
    private Long destId;

    public MessageReadEvent(Long srcId, Long destId) {
        this.srcId = srcId;
        this.destId = destId;
    }

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
