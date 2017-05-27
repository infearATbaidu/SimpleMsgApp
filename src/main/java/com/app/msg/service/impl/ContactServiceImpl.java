package com.app.msg.service.impl;

import com.app.msg.domain.entity.Contact;
import com.app.msg.domain.factory.ContactFactory;
import com.app.msg.interfaces.AddContactReq;
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
    public boolean addContact(AddContactReq req, Long curId) {
        if (req.getUserId().equals(curId)) {
            return false;
        }
        Contact contact = ContactFactory.create(req.getUserId(), curId);
        contactRepository.save(contact);
        messagingTemplate.convertAndSend(CONTACTS + curId, "");
        messagingTemplate.convertAndSend(CONTACTS + req.getUserId(), "");
        return true;
    }
}
