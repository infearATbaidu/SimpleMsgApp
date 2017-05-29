package com.app.msg.service;

import com.app.msg.interfaces.request.UpdateContactReq;

/**
 * Created by infear on 2017/5/27.
 */
public interface ContactService {
    boolean updateContact(UpdateContactReq req, Long curId);
}
