package com.app.msg.interfaces;

/**
 * Created by infear on 2017/5/26.
 */
public class UpdateContactReq {
    private Long userId;
    private boolean add;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }
}
