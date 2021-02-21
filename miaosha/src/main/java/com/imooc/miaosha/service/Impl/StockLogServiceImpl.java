package com.imooc.miaosha.service.Impl;

import com.imooc.miaosha.dataobject.StockLog;
import com.imooc.miaosha.dto.StockLogDTO;
import com.imooc.miaosha.enums.StockLogStatusEnum;
import com.imooc.miaosha.repository.StockLogRepository;
import com.imooc.miaosha.service.StockLogService;
import com.imooc.miaosha.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author DateBro
 * @Date 2021/2/21 12:26
 */
@Service
@Slf4j
public class StockLogServiceImpl implements StockLogService {

    @Autowired
    private StockLogRepository stockLogRepository;

    @Override
    @Transactional
    public StockLogDTO initStockLog(Integer productId, Integer productQuantity) {
        StockLog stockLog = new StockLog();
        stockLog.setProductId(productId);
        stockLog.setQuantity(productQuantity);
        stockLog.setStatus(StockLogStatusEnum.INIT.getStatus());
        String stockLogId = KeyUtil.genUniqueKey();
        stockLog.setStockLogId(stockLogId);
        stockLogRepository.save(stockLog);

        StockLogDTO stockLogDTO = new StockLogDTO();
        BeanUtils.copyProperties(stockLog, stockLogDTO);
        return stockLogDTO;
    }

    @Override
    public void updateStockLogStatus(String stockLogId, Integer oldStatus, Integer newStatus) {
        stockLogRepository.updateStockLogStatus(stockLogId, oldStatus, newStatus);
    }

    @Override
    public StockLogDTO getStockLogDTOByStockLogId(String stockLogId) {
        StockLog stockLog = stockLogRepository.getOne(stockLogId);
        StockLogDTO stockLogDTO = new StockLogDTO();
        BeanUtils.copyProperties(stockLog, stockLogDTO);
        return stockLogDTO;
    }
}
