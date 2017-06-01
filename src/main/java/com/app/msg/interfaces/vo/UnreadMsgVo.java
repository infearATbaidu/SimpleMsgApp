package com.app.msg.interfaces.vo;

/**
 * Created by infear on 2017/6/1.
 */
public class UnreadMsgVo {
    private Long srcId;
    private boolean resetFlag;

    public UnreadMsgVo() {
    }

    public UnreadMsgVo(Long srcId, boolean resetFlag) {
        this.srcId = srcId;
        this.resetFlag = resetFlag;
    }

    public Long getSrcId() {
        return srcId;
    }

    public void setSrcId(Long srcId) {
        this.srcId = srcId;
    }

    public boolean isResetFlag() {
        return resetFlag;
    }

    public void setResetFlag(boolean resetFlag) {
        this.resetFlag = resetFlag;
    }
}
