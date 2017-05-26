package com.app.msg.security;

/**
 * Created by infear on 2017/5/26.
 */
public class UserSessionInfo {
    private Long id;
    private String name;

    public UserSessionInfo(Long userId, String name) {
        this.id = userId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
