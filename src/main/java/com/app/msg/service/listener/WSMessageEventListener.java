package com.app.msg.service.listener;

import com.app.msg.common.contants.Constants;
import com.app.msg.interfaces.vo.UnreadMsgVo;
import com.app.msg.service.ContactService;
import com.app.msg.service.listener.event.ContactsRefreshedEvent;
import com.app.msg.service.listener.event.MessageReachedEvent;
import com.app.msg.service.listener.event.MessageReadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by infear on 2017/6/2.
 */
@Component
public class WSMessageEventListener {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ContactService contactService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @EventListener
    @Async
    public void handleContactsRefreshedEvent(ContactsRefreshedEvent event) {
        logger.info("handleContactsRefreshedEvent");
        messagingTemplate.convertAndSend(Constants.CONTACTS_BROKER + event.getUserIdL(), "");
        messagingTemplate.convertAndSend(Constants.CONTACTS_BROKER + event.getUserIdS(), "");
    }

    @EventListener
    @Async
    public void handleMessageReachedEvent4Contact(MessageReachedEvent event) {
        logger.info("handleMessageReachedEvent4Contact");
        contactService.updateContact(event.getDestId(), event.getSrcId(), true);
    }

    @EventListener
    @Async
    public void handleMessageReachedEvent4WS(MessageReachedEvent event) {
        logger.info("handleMessageReachedEvent4WS");
        messagingTemplate.convertAndSend(Constants.MSG_BROKER + event.getDestId(), event.getMsgVO());
        messagingTemplate.convertAndSend(Constants.MSG_UNREAD_BROKER + event.getDestId(), new UnreadMsgVo(event.getSrcId(), false));
    }

    @EventListener
    @Async
    public void handleMessageReadEvent(MessageReadEvent event) {
        logger.info("handleMessageReadEvent");
        messagingTemplate.convertAndSend(Constants.MSG_UNREAD_BROKER + event.getSrcId(), new UnreadMsgVo(event.getDestId(), true));
    }
}
