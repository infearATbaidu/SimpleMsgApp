package com.app.msg.service.listener;

import com.app.msg.common.contants.Constants;
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
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @EventListener
    @Async
    public void handleContactsRefreshedEvent(ContactsRefreshedEvent event) {
        logger.info("handleContactsRefreshedEvent");
        messagingTemplate.convertAndSend(Constants.CONTACTS_BROKER + event.getUserIdL(), "");
        messagingTemplate.convertAndSend(Constants.CONTACTS_BROKER + event.getUserIdS(), "");
    }
}
