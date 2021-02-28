package com.imooc.miaosha.repository;

import com.imooc.miaosha.dataobject.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.Optional;

/**
 * @Author DateBro
 * @Date 2021/2/17 18:40
 */
public interface ProductStockRepository extends JpaRepository<ProductStock, Integer> {
    ProductStock findByProductId(Integer productId);

    @Modifying
    @Query("update ProductStock s set s.stock=?2 where s.productId=?1 and s.stock=?3")
    void updateStock(Integer productId, Integer newStock, Integer oldStock);

    @Query(value = "select * from product_stock s where s.product_id = ?1 for update", nativeQuery = true)
    Optional<ProductStock> findByProductIdPessimistic(Integer productId);
}
