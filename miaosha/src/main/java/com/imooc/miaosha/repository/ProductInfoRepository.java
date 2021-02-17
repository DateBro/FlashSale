package com.imooc.miaosha.repository;

import com.imooc.miaosha.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author DateBro
 * @Date 2021/2/17 17:50
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer> {
}
