package com.imooc.miaosha.repository;

import com.imooc.miaosha.dataobject.PromoInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author DateBro
 * @Date 2021/2/18 14:40
 */
public interface PromoInfoRepository extends JpaRepository<PromoInfo, Integer> {
    PromoInfo findByProductId(Integer productId);
}
