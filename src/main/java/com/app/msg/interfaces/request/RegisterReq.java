package com.app.msg.interfaces.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by infear on 2017/5/26.
 */
public class RegisterReq {
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordConfirm;

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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
