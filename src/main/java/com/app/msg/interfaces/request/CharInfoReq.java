package com.app.msg.interfaces.request;

/**
 * Created by infear on 2017/5/30.
 */
public class CharInfoReq {
    private Long userId2Chat;

    public CharInfoReq() {
    }

    public Long getUserId2Chat() {
        return userId2Chat;
    }

    public void setUserId2Chat(Long userId2Chat) {
        this.userId2Chat = userId2Chat;
    }
}
