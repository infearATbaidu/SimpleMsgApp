package com.app.msg.service.listener.event;

import com.app.msg.domain.entity.Msg;

import java.util.List;

/**
 * Created by infear on 2017/6/2.
 */
public class MessageReadEvent {
    private Long srcId;
    private Long destId;
    private List<Msg> unreadMsgs;

    public MessageReadEvent(Long srcId, Long destId, List<Msg> unreadMsgs) {
        this.srcId = srcId;
        this.destId = destId;
        this.unreadMsgs = unreadMsgs;
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

    public List<Msg> getUnreadMsgs() {
        return unreadMsgs;
    }

    public void setUnreadMsgs(List<Msg> unreadMsgs) {
        this.unreadMsgs = unreadMsgs;
    }
}
