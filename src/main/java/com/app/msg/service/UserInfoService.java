package com.app.msg.service;

import com.app.msg.interfaces.LoginReq;
import com.app.msg.interfaces.RegisterReq;

/**
 * Created by infear on 2017/5/25.
 */
public interface UserInfoService {
    boolean login(LoginReq req);

    String register(RegisterReq req);
}
