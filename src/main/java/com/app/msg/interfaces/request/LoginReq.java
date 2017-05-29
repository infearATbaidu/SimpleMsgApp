package com.app.msg.interfaces.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by infear on 2017/5/26.
 */
public class LoginReq {
    @NotBlank
    private String name;
    @NotBlank
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
