package com.app.msg.interfaces.request;

/**
 * Created by infear on 2017/5/26.
 */
public class UpdateContactReq {
    private Long userId;
    private boolean isAdd;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(boolean add) {
        this.isAdd = add;
    }
}
