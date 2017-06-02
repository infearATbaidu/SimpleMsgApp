package com.app.msg.domain.factory;

import com.app.msg.common.contants.MsgStatus;
import com.app.msg.domain.entity.Msg;
import com.app.msg.interfaces.request.SendMsg;

import java.util.Date;

/**
 * Created by infear on 2017/5/30.
 */
public class MsgFactory {
    public static Msg create(SendMsg req) {
        Msg msg = new Msg();
        msg.setContent(req.getContent());
        msg.setSrcId(req.getSrcId());
        msg.setDestId(req.getDestId());
        msg.setStatus(MsgStatus.UNREAD);
        msg.setDelete(false);
        msg.setCreatedTime(new Date());
        return msg;
    }
}
