package com.app.msg.repo;

import com.app.msg.domain.dto.UserToMsgCntDTO;
import com.app.msg.domain.entity.Msg;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by infear on 2017/5/30.
 */
@Repository
public interface MsgRepository extends CrudRepository<Msg, Long> {
    @Query("select m from Msg m where ((m.srcId = ?1 and m.destId=?2) or (m.srcId = ?2 and m.destId=?1)) and m.isDelete=false order by m.createdTime")
    List<Msg> queryMsg(Long srcId, Long destId);

    @Query("select new com.app.msg.domain.dto.UserToMsgCntDTO(m.srcId,count(m)) from Msg m where m.destId = ?1 and m.srcId in ?2 and status = -1 group by m.srcId")
    List<UserToMsgCntDTO> queryUnreadMsgCount(Long id, Set<Long> contactIds);

    @Query("select m from Msg m where m.id=?1")
    Msg findOneForUpdate(Long msgId);
}
