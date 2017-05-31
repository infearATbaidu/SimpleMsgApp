package com.app.msg.controller;

import com.app.msg.common.UserSessionInfo;
import com.app.msg.common.log.LoggerOut;
import com.app.msg.config.WebSecurityConfig;
import com.app.msg.domain.entity.User;
import com.app.msg.interfaces.request.CharInfoReq;
import com.app.msg.interfaces.request.QueryMsg;
import com.app.msg.interfaces.request.SendMsg;
import com.app.msg.interfaces.vo.ChatInfoVO;
import com.app.msg.interfaces.vo.MsgVO;
import com.app.msg.service.ContactService;
import com.app.msg.service.MsgService;
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
public class ChartController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private MsgService msgService;

    @GetMapping("/chart")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) UserSessionInfo info, Long id, Model model) {
        model.addAttribute("info", info);
        model.addAttribute("userChatWith", userInfoService.findById(id));
        return "chart";
    }

    @PostMapping("/getChartInfo")
    @LoggerOut
    public @ResponseBody
    ChatInfoVO getChartInfo(@RequestBody CharInfoReq req, @SessionAttribute(WebSecurityConfig.SESSION_KEY) UserSessionInfo info) {
        User user = userInfoService.findById(req.getUserId2Chat());
        return new ChatInfoVO(info.getId(), info.getName(), user.getId(), user.getName());
    }

    @PostMapping("/sendMsg")
    @LoggerOut
    public
    @ResponseBody
    MsgVO sendMsg(@RequestBody SendMsg req, @SessionAttribute(WebSecurityConfig.SESSION_KEY) UserSessionInfo info) {
        return msgService.sendMsg(req);
    }

    @PostMapping("/queryMsgHistory")
    @LoggerOut
    public
    @ResponseBody
    List<MsgVO> queryMsgHistory(@RequestBody QueryMsg req) {
        return msgService.queryMsg(req);
    }
}
