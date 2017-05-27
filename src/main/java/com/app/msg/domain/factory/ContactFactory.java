package com.app.msg.domain.factory;

import com.app.msg.domain.entity.Contact;

/**
 * Created by infear on 2017/5/27.
 */
public class ContactFactory {
    public static Contact create(Long userId, Long curId) {
        Contact contact = new Contact();
        contact.setUserIdS(Math.min(curId, userId));
        contact.setUserIdL(Math.max(curId, userId));
        return contact;
    }
}
