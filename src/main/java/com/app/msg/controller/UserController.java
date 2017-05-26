package com.app.msg.controller;

import com.app.msg.interfaces.LoginReq;
import com.app.msg.interfaces.RegisterReq;
import com.app.msg.security.WebSecurityConfig;
import com.app.msg.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by infear on 2017/5/25.
 */
@Controller
public class UserController {
    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/registerPost")
    public
    @ResponseBody
    Map<String, Object> registerPost(@RequestBody RegisterReq req, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        String msg = userInfoService.register(req);
        if (!StringUtils.isEmpty(msg)) {
            result.put("success", false);
            result.put("message", msg);
            return result;
        }
        result.put("success", true);
        session.setAttribute(WebSecurityConfig.SESSION_KEY, req.getName());
        return result;
    }


    @PostMapping("/loginPost")
    public
    @ResponseBody
    Map<String, Object> loginPost(@RequestBody LoginReq req, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        if (!userInfoService.login(req)) {
            result.put("success", false);
            result.put("message", "Login Failed!");
            return result;
        }
        result.put("success", true);
        session.setAttribute(WebSecurityConfig.SESSION_KEY, req.getName());
        return result;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "redirect:/login";
    }
}