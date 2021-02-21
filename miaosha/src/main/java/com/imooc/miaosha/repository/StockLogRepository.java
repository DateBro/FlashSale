package com.imooc.miaosha.repository;

import com.imooc.miaosha.dataobject.StockLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author DateBro
 * @Date 2021/2/21 12:19
 */
public interface StockLogRepository extends JpaRepository<StockLog, String> {
    @Modifying
    @Query("update StockLog s set s.status=?3 where  s.stockLogId=?1 and s.status=?2")
    void updateStockLogStatus(String stockLogId, Integer oldStatus, Integer newStatus);
}
