package com.imooc.miaosha.repository;

import com.imooc.miaosha.dataobject.BuyerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author DateBro
 * @Date 2021/2/16 16:44
 */
public interface BuyerInfoRepository extends JpaRepository<BuyerInfo, Integer> {
    BuyerInfo findByTelephone(String telephone);
}
