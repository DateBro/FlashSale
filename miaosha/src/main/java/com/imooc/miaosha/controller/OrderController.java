package com.imooc.miaosha.controller;

import com.imooc.miaosha.dto.BuyerDTO;
import com.imooc.miaosha.dto.OrderDTO;
import com.imooc.miaosha.enums.ResultEnum;
import com.imooc.miaosha.exception.MiaoshaException;
import com.imooc.miaosha.service.Impl.OrderServiceImpl;
import com.imooc.miaosha.utils.ResultVOUtil;
import com.imooc.miaosha.viewobject.OrderVO;
import com.imooc.miaosha.viewobject.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    private OrderServiceImpl orderService;

    @PostMapping("/create")
    public ResultVO create(@RequestParam(value = "productId", required = true) Integer productId,
                           @RequestParam(value = "productQuantity", required = true) Integer productQuantity,
                           @RequestParam(value = "promoId", required = false) Integer promoId) {
        // 首先检查用户是否已登录
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        BuyerDTO buyerDTO = (BuyerDTO) httpServletRequest.getSession().getAttribute("LOGIN_BUYER");
        if (isLogin == null || !isLogin.booleanValue() || buyerDTO == null) {
            throw new MiaoshaException(ResultEnum.USER_NOT_LOGIN_ERROR);
        }

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerId(buyerDTO.getBuyerId());
        orderDTO.setProductId(productId);
        orderDTO.setProductQuantity(productQuantity);

        OrderDTO resultOrderDTO = orderService.create(orderDTO, promoId);
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(resultOrderDTO, orderVO);

        return ResultVOUtil.success(orderVO);
    }
}
