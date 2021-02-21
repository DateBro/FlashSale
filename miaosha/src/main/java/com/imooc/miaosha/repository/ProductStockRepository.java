package com.imooc.miaosha.repository;

import com.imooc.miaosha.dataobject.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author DateBro
 * @Date 2021/2/17 18:40
 */
public interface ProductStockRepository extends JpaRepository<ProductStock, Integer> {
    ProductStock findByProductId(Integer productId);

    @Modifying
    @Query("update ProductStock s set s.stock=?2 where s.productId=?1 and s.stock=?3")
    void updateStock(Integer productId, Integer newStock, Integer oldStock);
}
