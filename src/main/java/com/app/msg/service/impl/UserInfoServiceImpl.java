package com.app.msg.service.impl;

import com.app.msg.common.MD5Utils;
import com.app.msg.domain.entity.User;
import com.app.msg.domain.factory.UserFactory;
import com.app.msg.interfaces.LoginReq;
import com.app.msg.interfaces.RegisterReq;
import com.app.msg.repo.UserRepository;
import com.app.msg.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by infear on 2017/5/25.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean login(LoginReq req) {
        if (req == null) {
            return false;
        }
        User user = userRepository.findByName(req.getName());
        if (user == null || !user.getPassword().equals(MD5Utils.md5(req.getPassword()))) {
            return false;
        }
        return true;
    }

    @Override
    public String register(RegisterReq req) {
        if (req == null) {
            return "Request illegal.";
        }
        if (userRepository.findByName(req.getName()) != null) {
            return "Username already exists.";
        }
        if (!req.getPassword().equals(req.getPasswordConfirm())) {
            return "password inconsistent.";
        }
        User user = UserFactory.create(req);
        userRepository.save(user);
        return null;
    }
}
