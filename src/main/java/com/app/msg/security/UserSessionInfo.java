package com.app.msg.security;

/**
 * Created by infear on 2017/5/26.
 */
public class UserSessionInfo {
    private Long userId;
    private String username;

    public UserSessionInfo(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
