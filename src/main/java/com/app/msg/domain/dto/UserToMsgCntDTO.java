package com.app.msg.domain.dto;

/**
 * Created by infear on 2017/5/31.
 */
public class UserToMsgCntDTO {
    private Long userId;
    private Long cnt;

    public UserToMsgCntDTO(Long userId, Long cnt) {
        this.userId = userId;
        this.cnt = cnt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCnt() {
        return cnt;
    }

    public void setCnt(Long cnt) {
        this.cnt = cnt;
    }
}
