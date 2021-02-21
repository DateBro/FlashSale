package com.imooc.miaosha.controller;

import com.imooc.miaosha.constant.CookieConstant;
import com.imooc.miaosha.constant.RedisConstant;
import com.imooc.miaosha.dto.BuyerDTO;
import com.imooc.miaosha.dto.OrderDTO;
import com.imooc.miaosha.dto.StockLogDTO;
import com.imooc.miaosha.enums.ResultEnum;
import com.imooc.miaosha.exception.MiaoshaException;
import com.imooc.miaosha.mq.MqProducer;
import com.imooc.miaosha.service.Impl.OrderServiceImpl;
import com.imooc.miaosha.service.Impl.PromoServiceImpl;
import com.imooc.miaosha.service.Impl.StockLogServiceImpl;
import com.imooc.miaosha.utils.CookieUtil;
import com.imooc.miaosha.utils.ResultVOUtil;
import com.imooc.miaosha.viewobject.OrderVO;
import com.imooc.miaosha.viewobject.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.*;

/**
 * @Author DateBro
 * @Date 2021/2/17 22:32
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MqProducer mqProducer;

    @Autowired
    private StockLogServiceImpl stockLogService;

    @Autowired
    private PromoServiceImpl promoService;

    private ExecutorService executorService;

    @PostConstruct
    void init() {
        executorService = Executors.newFixedThreadPool(20);
    }

    @PostMapping("/create")
    public ResultVO create(@RequestParam(value = "productId", required = true) Integer productId,
                           @RequestParam(value = "productQuantity", required = true) Integer productQuantity,
                           @RequestParam(value = "promoId", required = false) Integer promoId,
                           @RequestParam(value = "promoToken", required = true) String promoToken) {
        Cookie cookie = CookieUtil.get(httpServletRequest, CookieConstant.TOKEN);
        BuyerDTO buyerDTO = (BuyerDTO) redisTemplate.opsForValue().get(cookie.getValue());

        //校验秒杀令牌是否正确
        if (promoId != null) {
            String inRedisPromoToken = (String) redisTemplate.opsForValue().get(String.format(RedisConstant.PROMO_PRODUCT_TOKEN_PATTER, productId, promoId, buyerDTO.getBuyerId()));
            if (inRedisPromoToken == null || !StringUtils.equals(inRedisPromoToken, promoToken))
                throw new MiaoshaException(ResultEnum.PROMO_TOKEN_INVALID);
        }

        // 队列化泄洪
        Future<Object> future = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setBuyerId(buyerDTO.getBuyerId());
                orderDTO.setProductId(productId);
                orderDTO.setProductQuantity(productQuantity);

                // 初始化库存流水
                StockLogDTO stockLogDTO = stockLogService.initStockLog(productId, productQuantity);
                boolean result = mqProducer.transactionAsyncReduceStock(orderDTO, promoId, stockLogDTO);
                if (!result) throw new MiaoshaException(ResultEnum.CREATE_ORDER_FAIL);

                return null;
            }
        });

        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new MiaoshaException(ResultEnum.UNKNOWN_ERROR);
        } catch (ExecutionException e) {
            e.printStackTrace();
            throw new MiaoshaException(ResultEnum.UNKNOWN_ERROR);
        }

        return ResultVOUtil.success();
    }

    @PostMapping("/genToken")
    public ResultVO genToken(@RequestParam(value = "productId", required = true) Integer productId,
                             @RequestParam(value = "promoId", required = true) Integer promoId) {
        Cookie cookie = CookieUtil.get(httpServletRequest, CookieConstant.TOKEN);
        BuyerDTO buyerDTO = (BuyerDTO) redisTemplate.opsForValue().get(cookie.getValue());

        //获取秒杀访问令牌
        String promoToken = promoService.genPromoToken(promoId, productId, buyerDTO.getBuyerId());
        if (promoToken == null) {
            log.error("【生成秒杀令牌】生成令牌失败");
            throw new MiaoshaException(ResultEnum.PARAMETER_VALIDATION_ERROR);
        }
        return ResultVOUtil.success(promoToken);
    }
}
