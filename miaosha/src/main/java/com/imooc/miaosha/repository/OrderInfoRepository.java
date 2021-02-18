package com.imooc.miaosha.repository;

import com.imooc.miaosha.dataobject.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author DateBro
 * @Date 2021/2/17 22:37
 */
public interface OrderInfoRepository extends JpaRepository<OrderInfo, String> {
    List<OrderInfo> findAllByBuyerId(Integer buyerId);
}
