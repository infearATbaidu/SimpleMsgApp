package com.app.msg.domain.factory;

import com.app.msg.common.utils.MD5Utils;
import com.app.msg.domain.entity.User;
import com.app.msg.interfaces.request.RegisterReq;

import java.util.Date;

/**
 * Created by infear on 2017/5/26.
 */
public class UserFactory {
    public static User create(RegisterReq req) {
        User user = new User();
        user.setName(req.getName());
        user.setPassword(MD5Utils.md5(req.getPassword()));
        user.setCreatedTime(new Date());
        return user;
    }
}
