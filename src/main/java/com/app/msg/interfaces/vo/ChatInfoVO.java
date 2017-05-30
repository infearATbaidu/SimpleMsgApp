package com.app.msg.interfaces.vo;

/**
 * Created by infear on 2017/5/30.
 */
public class ChatInfoVO {
    private Long srcId;
    private String srcName;
    private Long destId;
    private String destName;

    public ChatInfoVO(Long srcId, String srcName, Long destId, String destName) {
        this.srcId = srcId;
        this.srcName = srcName;
        this.destId = destId;
        this.destName = destName;
    }

    public ChatInfoVO() {
    }

    public Long getSrcId() {
        return srcId;
    }

    public void setSrcId(Long srcId) {
        this.srcId = srcId;
    }

    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public Long getDestId() {
        return destId;
    }

    public void setDestId(Long destId) {
        this.destId = destId;
    }

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName = destName;
    }
}
