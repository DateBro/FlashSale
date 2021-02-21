package com.imooc.miaosha.service;

import com.imooc.miaosha.dto.StockLogDTO;

/**
 * @Author DateBro
 * @Date 2021/2/21 12:22
 */
public interface StockLogService {

    StockLogDTO initStockLog(Integer productId, Integer productQuantity);

    void updateStockLogStatus(String stockLogId, Integer oldStatus, Integer newStatus);

    StockLogDTO getStockLogDTOByStockLogId(String stockLogId);
}
