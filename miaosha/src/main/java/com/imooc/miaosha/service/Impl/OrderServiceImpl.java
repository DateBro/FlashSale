package com.imooc.miaosha.service.Impl;

import com.imooc.miaosha.dataobject.BuyerInfo;
import com.imooc.miaosha.dataobject.OrderInfo;
import com.imooc.miaosha.dataobject.ProductInfo;
import com.imooc.miaosha.dto.OrderDTO;
import com.imooc.miaosha.enums.ResultEnum;
import com.imooc.miaosha.exception.MiaoshaException;
import com.imooc.miaosha.repository.BuyerInfoRepository;
import com.imooc.miaosha.repository.OrderInfoRepository;
import com.imooc.miaosha.repository.ProductInfoRepository;
import com.imooc.miaosha.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

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
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private BuyerInfoRepository buyerInfoRepository;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private SequenceServiceImpl sequenceService;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        // 1. 检验参数，比如用户id是否合法，商品是否存在，数量是否正确
        Optional<BuyerInfo> buyerInfo = buyerInfoRepository.findById(orderDTO.getBuyerId());
        if (!buyerInfo.isPresent()) {
            log.error("【创建订单】用户不存在");
            throw new MiaoshaException(ResultEnum.USER_NOT_LOGIN_ERROR);
        }
        Optional<ProductInfo> productInfo = productInfoRepository.findById(orderDTO.getProductId());
        if (!productInfo.isPresent()) {
            log.error("【创建订单】商品不存在");
            throw new MiaoshaException(ResultEnum.PARAMETER_VALIDATION_ERROR);
        }
        if (orderDTO.getProductQuantity() <= 0 || orderDTO.getProductQuantity() > 99) {
            log.error("【创建订单】下单商品数量有误");
            throw new MiaoshaException(ResultEnum.PARAMETER_VALIDATION_ERROR);
        }

        // 2. 落单减库存
        productService.decreaseStock(orderDTO);

        // 3. 订单入库
        String orderId = sequenceService.genUniqueOrderId();
        orderDTO.setProductPrice(productInfo.get().getProductPrice());
        orderDTO.setOrderAmount(orderDTO.getProductPrice().multiply(new BigDecimal(orderDTO.getProductQuantity())));
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderStatus(0);
        orderDTO.setPayStatus(0);

        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderDTO, orderInfo);
        orderInfoRepository.save(orderInfo);

        // 记得修改销量
        productService.increaseSales(orderDTO);

        // 4. 返回前端
        return orderDTO;
    }
}
