package com.app.msg.service.listener;

import com.app.msg.interfaces.vo.MsgVO;

/**
 * Created by infear on 2017/6/2.
 */
public class MessageReachedEvent {
    private Long srcId;
    private Long destId;
    private MsgVO msgVO;

    public MessageReachedEvent(Long srcId, Long destId, MsgVO msgVO) {
        this.srcId = srcId;
        this.destId = destId;
        this.msgVO = msgVO;
    }

    public MessageReachedEvent() {
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

    public MsgVO getMsgVO() {
        return msgVO;
    }

    public void setMsgVO(MsgVO msgVO) {
        this.msgVO = msgVO;
    }

}
