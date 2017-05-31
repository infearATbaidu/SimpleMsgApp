package com.app.msg.service.impl;

import com.app.msg.common.Constants;
import com.app.msg.common.ContactStatus;
import com.app.msg.common.MsgStatus;
import com.app.msg.common.utils.DateTimeUtils;
import com.app.msg.domain.entity.Contact;
import com.app.msg.domain.entity.Msg;
import com.app.msg.domain.factory.ContactFactory;
import com.app.msg.domain.factory.MsgFactory;
import com.app.msg.interfaces.request.QueryMsg;
import com.app.msg.interfaces.request.SendMsg;
import com.app.msg.interfaces.vo.MsgVO;
import com.app.msg.repo.ContactRepository;
import com.app.msg.repo.MsgRepository;
import com.app.msg.service.MsgService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by infear on 2017/5/30.
 */
@Service
public class MsgServiceImpl implements MsgService {
    @Autowired
    private MsgRepository msgRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public MsgVO sendMsg(SendMsg req) {
        Msg msg = MsgFactory.create(req);
        msgRepository.save(msg);
        addContacts(req.getSrcId(), req.getDestId());
        MsgVO vo = convert2MsgVO(msg);
        messagingTemplate.convertAndSend(Constants.MSG_BROKER + req.getDestId(), vo);
        return vo;
    }

    private void addContacts(Long srcId, Long destId) {
        Contact contact = contactRepository.findByUserIdSAndUserIdL(Math.min(srcId, destId), Math.max(srcId, destId));
        if (contact != null) {
            if (contact.getStatus() == ContactStatus.REMOVED) {
                contact.setStatus(ContactStatus.ADDED);
                contactRepository.save(contact);
                notifyContactToRefresh(srcId, destId);
            }
        } else {
            contact = ContactFactory.create(srcId, destId, ContactStatus.ADDED);
            contactRepository.save(contact);
            notifyContactToRefresh(srcId, destId);
        }
    }

    // 发送ws消息 Todo:User async
    private void notifyContactToRefresh(Long srcId, Long destId) {
        messagingTemplate.convertAndSend(Constants.CONTACTS_BROKER + srcId, "");
        messagingTemplate.convertAndSend(Constants.CONTACTS_BROKER + destId, "");
    }

    @Override
    public List<MsgVO> queryMsg(QueryMsg req) {
        List<Msg> msgList = msgRepository.queryMsg(req.getSrcId(), req.getDestId());
        if (CollectionUtils.isEmpty(msgList)) return null;
        List<MsgVO> result = Lists.newArrayList();
        List<Msg> unreadMsgs = Lists.newArrayList();
        for (Msg msg : msgList) {
            MsgVO vo = convert2MsgVO(msg);
            result.add(vo);
            // 如果是自己的未读消息，则置为已读
            if (msg.getDestId().equals(req.getSrcId()) && msg.getStatus() == MsgStatus.UNREAD) {
                unreadMsgs.add(msg);
                msg.setStatus(MsgStatus.READ);
            }
        }
        if (!CollectionUtils.isEmpty(unreadMsgs)) {
            markAsRead(req.getSrcId(), unreadMsgs);
        }
        return result;
    }

    private MsgVO convert2MsgVO(Msg msg) {
        MsgVO vo = new MsgVO();
        vo.setId(msg.getId());
        vo.setSrcId(msg.getSrcId());
        vo.setDestId(msg.getDestId());
        vo.setContent(msg.getContent());
        vo.setTime(DateTimeUtils.displayDateTime(msg.getCreatedTime()));
        return vo;
    }

    //Todo:use async
    private void markAsRead(Long srcId, List<Msg> unreadMsgs) {
        msgRepository.save(unreadMsgs);
        messagingTemplate.convertAndSend(Constants.MSG_BROKER + srcId, "");
    }
}
