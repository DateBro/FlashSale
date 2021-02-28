package com.imooc.miaosha.service.Impl;

import com.imooc.miaosha.dataobject.OrderInfo;
import com.imooc.miaosha.dto.BuyerDTO;
import com.imooc.miaosha.dto.OrderDTO;
import com.imooc.miaosha.dto.ProductDTO;
import com.imooc.miaosha.dto.StockLogDTO;
import com.imooc.miaosha.enums.ResultEnum;
import com.imooc.miaosha.enums.StockLogStatusEnum;
import com.imooc.miaosha.exception.MiaoshaException;
import com.imooc.miaosha.repository.OrderInfoRepository;
import com.imooc.miaosha.service.OrderService;
import com.imooc.miaosha.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @Author DateBro
 * @Date 2021/2/18 10:43
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private BuyerServiceImpl buyerService;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private SequenceServiceImpl sequenceService;

    @Autowired
    private StockLogServiceImpl stockLogService;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO, Integer promoId, StockLogDTO stockLogDTO) {
        // 1. 检验参数，比如用户id是否合法，商品是否存在，数量是否正确
        // 使用秒杀令牌后能调用这里的请求都是合法的
        ProductDTO productDTO = productService.getProductDetailInCache(orderDTO.getProductId());
        if (productDTO == null) {
            log.error("【创建订单】商品不存在");
            throw new MiaoshaException(ResultEnum.PARAMETER_VALIDATION_ERROR);
        }
        if (orderDTO.getProductQuantity() <= 0 || orderDTO.getProductQuantity() > 99) {
            log.error("【创建订单】下单商品数量有误");
            throw new MiaoshaException(ResultEnum.PARAMETER_VALIDATION_ERROR);
        }

        productService.decreaseStockInCache(orderDTO);

        // 3. 订单入库
        // jmeter总是报错重复的orderId，检查后通过update操作加锁解决问题
//        String orderId = sequenceService.genUniqueOrderId();
        String orderId = KeyUtil.genUniqueKey();
        if (promoId != null) {
            orderDTO.setProductPrice(productDTO.getPromoDTO().getPromoProductPrice());
        } else {
            orderDTO.setProductPrice(productDTO.getProductPrice());
        }
        orderDTO.setOrderAmount(orderDTO.getProductPrice().multiply(new BigDecimal(orderDTO.getProductQuantity())));
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderStatus(0);
        orderDTO.setPayStatus(0);

        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderDTO, orderInfo);
        orderInfoRepository.save(orderInfo);

        // 记得修改销量
        productService.increaseSales(orderDTO);

        // 修改库存流水，修改为已下单状态
        stockLogService.updateStockLogStatus(stockLogDTO.getStockLogId(), stockLogDTO.getStatus(), StockLogStatusEnum.COMMIT.getStatus());

        // 4. 返回前端
        return orderDTO;
    }


}
