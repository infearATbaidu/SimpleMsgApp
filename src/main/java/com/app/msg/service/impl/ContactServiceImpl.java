package com.app.msg.service.impl;

import com.app.msg.common.contants.ContactStatus;
import com.app.msg.config.AppEventPublisher;
import com.app.msg.domain.entity.Contact;
import com.app.msg.domain.factory.ContactFactory;
import com.app.msg.repo.ContactRepository;
import com.app.msg.service.ContactService;
import com.app.msg.service.listener.event.ContactsRefreshedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by infear on 2017/5/27.
 */
@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private AppEventPublisher appEventPublisher;

    @Override
    public boolean updateContact(Long destId, Long srcId, boolean isAdd) {
        if (destId.equals(srcId)) {
            return false;
        }
        Contact contact = contactRepository.findByUserIdSAndUserIdL(Math.min(destId, srcId), Math.max(destId, srcId));
        if (contact != null) {
            contact.setStatus(isAdd ? ContactStatus.ADDED : ContactStatus.REMOVED);
            contactRepository.save(contact);
        } else if (isAdd) {
            contact = ContactFactory.create(destId, srcId, ContactStatus.ADDED);
            contactRepository.save(contact);
        }
        appEventPublisher.publish(new ContactsRefreshedEvent(destId, srcId));
        return true;
    }
}
