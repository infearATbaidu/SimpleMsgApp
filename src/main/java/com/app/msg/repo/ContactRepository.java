package com.app.msg.repo;

import com.app.msg.domain.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by infear on 2017/5/27.
 */
@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
    List<Contact> findByUserIdSAndStatus(Long userid, int status);

    List<Contact> findByUserIdLAndStatus(Long userid, int status);

}
