package com.app.msg.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by infear on 2017/5/26.
 */
@Entity
public class Contact {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "user_id_s")
    private Long userIdS;
    @Column(name = "user_id_l")
    private Long userIdL;
    private int status;
    @Column(name = "created_time")
    private Date createdTime;
    @Column(name = "updated_time")
    private Date updatedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserIdS() {
        return userIdS;
    }

    public void setUserIdS(Long userIdS) {
        this.userIdS = userIdS;
    }

    public Long getUserIdL() {
        return userIdL;
    }

    public void setUserIdL(Long userIdL) {
        this.userIdL = userIdL;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
