package com.app.msg.service.listener.event;

/**
 * Created by infear on 2017/6/2.
 * 联系人更新事件
 */
public class ContactsRefreshedEvent {
    private Long userIdS;
    private Long userIdL;

    public ContactsRefreshedEvent() {
    }

    public ContactsRefreshedEvent(Long userIdS, Long userIdL) {
        this.userIdS = userIdS;
        this.userIdL = userIdL;
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
}
