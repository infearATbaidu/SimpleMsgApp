package com.app.msg.repo;

import com.app.msg.domain.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by infear on 2017/5/25.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String username);

    List<User> findByNameLike(String namePattern);
}
