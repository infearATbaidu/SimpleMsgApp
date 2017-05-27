package com.app.msg.service;

import com.app.msg.interfaces.AddContactReq;

/**
 * Created by infear on 2017/5/27.
 */
public interface ContactService {
    boolean addContact(AddContactReq req, Long curId);
}
