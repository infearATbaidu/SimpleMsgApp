package com.app.msg.repo;

import com.app.msg.domain.entity.Msg;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by infear on 2017/5/30.
 */
@Repository
public interface MsgRepository extends CrudRepository<Msg, Long> {
    @Query("select m from Msg m where (m.srcId = ?1 and m.destId=?2) or (m.srcId = ?2 and m.destId=?1) order by m.createdTime")
    List<Msg> queryMsg(Long srcId, Long destId);
}
