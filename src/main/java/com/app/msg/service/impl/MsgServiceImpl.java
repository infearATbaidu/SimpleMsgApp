package com.app.msg.service.impl;

import com.app.msg.common.Constants;
import com.app.msg.common.ContactStatus;
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
    public Long sendMsg(SendMsg req) {
        Msg msg = MsgFactory.create(req);
        msgRepository.save(msg);
        addContacts(req.getSrcId(), req.getDestId());
        return msg.getId();
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
        for (Msg msg : msgList) {
            MsgVO vo = new MsgVO();
            vo.setId(msg.getId());
            vo.setSrcId(msg.getSrcId());
            vo.setDestId(msg.getDestId());
            vo.setContent(msg.getContent());
            vo.setTime(DateTimeUtils.displayDateTime(msg.getCreatedTime()));
            result.add(vo);
        }
        return result;
    }
}
