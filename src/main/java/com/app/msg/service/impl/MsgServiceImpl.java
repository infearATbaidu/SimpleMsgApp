package com.app.msg.service.impl;

import com.app.msg.common.contants.MsgStatus;
import com.app.msg.common.utils.DateTimeUtils;
import com.app.msg.domain.entity.Msg;
import com.app.msg.domain.factory.MsgFactory;
import com.app.msg.interfaces.request.DeleteMsgReq;
import com.app.msg.interfaces.request.QueryMsgReq;
import com.app.msg.interfaces.request.SendMsg;
import com.app.msg.interfaces.vo.MsgVO;
import com.app.msg.repo.MsgRepository;
import com.app.msg.service.MsgService;
import com.app.msg.service.listener.event.MessageReachedEvent;
import com.app.msg.service.listener.event.MessageReadEvent;
import com.app.msg.service.publisher.AppEventPublisher;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private AppEventPublisher appEventPublisher;

    @Override
    public MsgVO sendMsg(SendMsg req) {
        Msg msg = MsgFactory.create(req);
        msgRepository.save(msg);
        MsgVO vo = convert2MsgVO(msg);
        appEventPublisher.publish(new MessageReachedEvent(req.getSrcId(), req.getDestId(), vo));
        return vo;
    }

    @Override
    public List<MsgVO> queryMsg(QueryMsgReq req) {
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
            }
        }
        if (!CollectionUtils.isEmpty(unreadMsgs)) {
            appEventPublisher.publish(new MessageReadEvent(req.getSrcId(), req.getDestId(), unreadMsgs));
        }
        return result;
    }

    @Override
    @Transactional
    public boolean deleteMsg(DeleteMsgReq req) {
        Msg msg = msgRepository.findOneForUpdate(req.getMsgId());
        if (!req.getSrcId().equals(msg.getSrcId())) {
            return false;
        }
        msg.setDelete(true);
        msgRepository.save(msg);
        return true;
    }

    @Override
    public void markAsRead(List<Msg> unreadMsgs) {
        unreadMsgs.forEach(msg -> msg.setStatus(MsgStatus.READ));
        msgRepository.save(unreadMsgs);
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
}
