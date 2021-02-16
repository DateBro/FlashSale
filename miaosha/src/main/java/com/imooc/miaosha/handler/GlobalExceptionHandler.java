package com.imooc.miaosha.handler;

import com.imooc.miaosha.exception.MiaoshaException;
import com.imooc.miaosha.utils.ResultVOUtil;
import com.imooc.miaosha.viewobject.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author DateBro
 * @Date 2021/2/16 17:38
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MiaoshaException.class)
    public ResultVO handleMiaoshaException(MiaoshaException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }
}
