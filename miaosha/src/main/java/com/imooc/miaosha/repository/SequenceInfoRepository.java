package com.imooc.miaosha.repository;

import com.imooc.miaosha.dataobject.SequenceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * @Author DateBro
 * @Date 2021/2/18 11:26
 */
public interface SequenceInfoRepository extends JpaRepository<SequenceInfo, String> {
    @Modifying
    @Query("update SequenceInfo s set s.currentValue=?1 where  s.currentValue=?3 and s.name=?2")
    void updateCurrentValue(Integer currentValue, String sequenceId, Integer oldValue);
}
