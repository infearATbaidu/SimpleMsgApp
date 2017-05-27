package com.app.msg.service.impl;

import com.app.msg.common.MD5Utils;
import com.app.msg.common.UserSessionInfo;
import com.app.msg.config.WebSecurityConfig;
import com.app.msg.domain.entity.User;
import com.app.msg.domain.factory.UserFactory;
import com.app.msg.interfaces.LoginReq;
import com.app.msg.interfaces.RegisterReq;
import com.app.msg.interfaces.vo.UserVO;
import com.app.msg.repo.UserRepository;
import com.app.msg.service.UserInfoService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by infear on 2017/5/25.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean login(LoginReq req, HttpSession session) {
        if (req == null) {
            return false;
        }
        User user = userRepository.findByName(req.getName());
        if (user == null || !user.getPassword().equals(MD5Utils.md5(req.getPassword()))) {
            return false;
        }
        session.setAttribute(WebSecurityConfig.SESSION_KEY, new UserSessionInfo(user.getId(), user.getName()));
        return true;
    }

    @Override
    public String register(RegisterReq req, HttpSession session) {
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
        session.setAttribute(WebSecurityConfig.SESSION_KEY, new UserSessionInfo(user.getId(), user.getName()));
        return null;
    }

    @Override
    public List<UserVO> searchByName(String name, UserSessionInfo info) {
        List<User> users = userRepository.findByNameLike(name + "%");
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        List<UserVO> result = Lists.newArrayList();
        for (User user : users) {
            if (info.getId().equals(user.getId())) {
                continue;
            }
            UserVO vo = new UserVO();
            vo.setId(user.getId());
            vo.setName(user.getName());
            result.add(vo);
        }
        return result;
    }
}
