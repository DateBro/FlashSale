package com.imooc.miaosha.repository;

import com.imooc.miaosha.dataobject.BuyerPassword;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author DateBro
 * @Date 2021/2/16 16:47
 */
public interface BuyerPasswordRepository extends JpaRepository<BuyerPassword, Integer> {
    BuyerPassword findByBuyerId(Integer buyerId);
}
