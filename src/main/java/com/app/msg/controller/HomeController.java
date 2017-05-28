package com.app.msg.controller;

import com.app.msg.common.UserSessionInfo;
import com.app.msg.config.WebSecurityConfig;
import com.app.msg.interfaces.AddContactReq;
import com.app.msg.interfaces.ContactListReq;
import com.app.msg.interfaces.SearchReq;
import com.app.msg.interfaces.vo.UserVO;
import com.app.msg.service.ContactService;
import com.app.msg.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by infear on 2017/5/26.
 */
@Controller
public class HomeController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ContactService contactService;

    @GetMapping("/")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) UserSessionInfo info, Model model) {
        model.addAttribute("info", info);
        return "index";
    }

    @PostMapping("/search")
    public
    @ResponseBody
    List<UserVO> searchUsers(@RequestBody SearchReq req, @SessionAttribute(WebSecurityConfig.SESSION_KEY) UserSessionInfo info) {
        return userInfoService.searchByName(req.getName(), info);
    }

    @PostMapping("/contactList")
    public
    @ResponseBody
    List<UserVO> contactList(@RequestBody ContactListReq req, @SessionAttribute(WebSecurityConfig.SESSION_KEY) UserSessionInfo info) {
        return userInfoService.queryContacts(req, info);
    }

    @PostMapping("/addContact")
    public
    @ResponseBody
    boolean searchUsers(@RequestBody AddContactReq req, @SessionAttribute(WebSecurityConfig.SESSION_KEY) UserSessionInfo info) {
        return contactService.addContact(req, info.getId());
    }
}
