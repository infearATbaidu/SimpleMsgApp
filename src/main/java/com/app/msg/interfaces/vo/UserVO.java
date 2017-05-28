package com.app.msg.interfaces.vo;

/**
 * Created by infear on 2017/5/27.
 */
public class UserVO {
    private Long id;
    private String name;
    private boolean contactFlag;

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

    public boolean getContactFlag() {
        return contactFlag;
    }

    public void setContactFlag(boolean contactFlag) {
        this.contactFlag = contactFlag;
    }
}
