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

import java.util.Optional;
import java.util.UUID;

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
        // 因为KeyUtil的这个方法是synchronized，而且创建订单时也用到了这个方法生成id，所以会降低性能，创建订单的tps一开始在100左右上不去就是因为这里
//        String stockLogId = KeyUtil.genUniqueKey();
        String stockLogId = UUID.randomUUID().toString().replace("-","");
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
        // 之前使用的getOne方法，但getOne会报错，具体问题见笔记
        Optional<StockLog> stockLog = stockLogRepository.findById(stockLogId);
        if (!stockLog.isPresent()) {
            return null;
        } else {
            StockLogDTO stockLogDTO = new StockLogDTO();
            BeanUtils.copyProperties(stockLog.get(), stockLogDTO);
            return stockLogDTO;
        }
    }
}
