package com.imooc.miaosha.repository;

import com.imooc.miaosha.dataobject.SequenceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author DateBro
 * @Date 2021/2/18 11:26
 */
public interface SequenceInfoRepository extends JpaRepository<SequenceInfo, String> {
}
