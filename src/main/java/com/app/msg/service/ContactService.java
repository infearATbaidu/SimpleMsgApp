package com.app.msg.service;


/**
 * Created by infear on 2017/5/27.
 */
public interface ContactService {
    boolean updateContact(Long destId, Long curId, boolean isAdd);
}
