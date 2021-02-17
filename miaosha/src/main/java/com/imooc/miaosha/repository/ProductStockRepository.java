package com.imooc.miaosha.repository;

import com.imooc.miaosha.dataobject.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author DateBro
 * @Date 2021/2/17 18:40
 */
public interface ProductStockRepository extends JpaRepository<ProductStock, Integer> {
    ProductStock findByProductId(Integer productId);
}
