package com.imooc.miaosha.exception;

import com.imooc.miaosha.enums.ResultEnum;
import lombok.Data;
import lombok.Getter;

/**
 * @Author DateBro
 * @Date 2021/2/16 17:38
 */
@Data
public class MiaoshaException extends RuntimeException {
    private Integer code;

    public MiaoshaException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public MiaoshaException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
