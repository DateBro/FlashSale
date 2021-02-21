package com.imooc.miaosha.aspect;

import com.imooc.miaosha.constant.CookieConstant;
import com.imooc.miaosha.dto.BuyerDTO;
import com.imooc.miaosha.enums.ResultEnum;
import com.imooc.miaosha.exception.MiaoshaException;
import com.imooc.miaosha.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author DateBro
 * @Date 2021/2/21 17:49
 */
@Aspect
@Component
@Slf4j
public class BuyerAuthAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    @Pointcut("execution(public * com.imooc.miaosha.controller.OrderController.*(..))")
    public void verify() {}

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = requestAttributes.getRequest();
        // 先查询cookie
        Cookie cookie = CookieUtil.get(httpServletRequest, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登录校验】cookie中查不到token");
            throw new MiaoshaException(ResultEnum.USER_NOT_LOGIN_ERROR);
        }
        // 再查询redis
        BuyerDTO buyerDTO = (BuyerDTO) redisTemplate.opsForValue().get(cookie.getValue());
        if (buyerDTO == null) {
            log.warn("【登录校验】redis中查不到token");
            throw new MiaoshaException(ResultEnum.USER_NOT_LOGIN_ERROR);
        }
    }
}
