package com.app.msg.interfaces.request;

/**
 * Created by infear on 2017/5/26.
 */
public class UpdateContactReq {
    private Long userId;
    private boolean flag;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
