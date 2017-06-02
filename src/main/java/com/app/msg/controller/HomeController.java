package com.app.msg.controller;

import com.app.msg.common.UserSessionInfo;
import com.app.msg.common.log.LoggerOut;
import com.app.msg.config.WebSecurityConfig;
import com.app.msg.interfaces.request.ContactListReq;
import com.app.msg.interfaces.request.EmptyReq;
import com.app.msg.interfaces.request.SearchReq;
import com.app.msg.interfaces.request.UpdateContactReq;
import com.app.msg.interfaces.vo.UserVO;
import com.app.msg.service.ContactService;
import com.app.msg.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

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
    @LoggerOut
    public @ResponseBody
    List<UserVO> searchUsers(@RequestBody SearchReq req, @SessionAttribute(WebSecurityConfig.SESSION_KEY) UserSessionInfo info) {
        return userInfoService.searchByName(req.getName(), info);
    }

    @PostMapping("/contactList")
    @LoggerOut
    public
    @ResponseBody
    List<UserVO> contactList(@RequestBody ContactListReq req, @SessionAttribute(WebSecurityConfig.SESSION_KEY) UserSessionInfo info) {
        return userInfoService.queryContacts(req, info);
    }

    @PostMapping("/getUserInfo")
    @LoggerOut
    public
    @ResponseBody
    Long getUserInfo(@RequestBody EmptyReq req, @SessionAttribute(WebSecurityConfig.SESSION_KEY) UserSessionInfo info) {
        return info.getId();
    }

    @PostMapping("/updateContact")
    @LoggerOut
    public
    @ResponseBody
    boolean searchUsers(@RequestBody UpdateContactReq req, @SessionAttribute(WebSecurityConfig.SESSION_KEY) UserSessionInfo info) {
        return contactService.updateContact(req.getUserId(), info.getId(), req.getIsAdd());
    }
}
