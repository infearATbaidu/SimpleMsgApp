package com.app.msg.service.impl;

import com.app.msg.common.ContactStatus;
import com.app.msg.domain.entity.Contact;
import com.app.msg.domain.factory.ContactFactory;
import com.app.msg.interfaces.UpdateContactReq;
import com.app.msg.repo.ContactRepository;
import com.app.msg.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by infear on 2017/5/27.
 */
@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private static final String CONTACTS = "/app/contactList?id=";

    @Override
    public boolean updateContact(UpdateContactReq req, Long curUserId) {
        if (req.getUserId().equals(curUserId)) {
            return false;
        }
        Contact contact = contactRepository.findByUserIdSAndUserIdL(Math.min(curUserId, req.getUserId()), Math.max(curUserId, req.getUserId()));
        if (contact != null) {
            contact.setStatus(req.isAdd() ? ContactStatus.ADDED : ContactStatus.REMOVED);
            contactRepository.save(contact);
        } else if (req.isAdd()) {
            contact = ContactFactory.create(req.getUserId(), curUserId, req.isAdd() ? ContactStatus.ADDED : ContactStatus.REMOVED);
            contactRepository.save(contact);
        }
        messagingTemplate.convertAndSend(CONTACTS + curUserId, "");
        messagingTemplate.convertAndSend(CONTACTS + req.getUserId(), "");
        return true;
    }
}
