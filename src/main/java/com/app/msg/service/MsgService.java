package com.app.msg.service;

import com.app.msg.interfaces.request.QueryMsg;
import com.app.msg.interfaces.request.SendMsg;
import com.app.msg.interfaces.vo.MsgVO;

import java.util.List;

/**
 * Created by infear on 2017/5/30.
 */
public interface MsgService {
    MsgVO sendMsg(SendMsg req);

    List<MsgVO> queryMsg(QueryMsg req);
}
