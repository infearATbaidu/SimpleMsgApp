package com.app.msg.common;

import org.springframework.util.DigestUtils;

/**
 * Created by infear on 2017/5/26.
 */
public class MD5Utils {
    public static String md5(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
