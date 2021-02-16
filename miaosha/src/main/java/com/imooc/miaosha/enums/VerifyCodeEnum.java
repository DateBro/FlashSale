package com.imooc.miaosha.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @Author DateBro
 * @Date 2021/2/16 18:06
 */
@Getter
public enum VerifyCodeEnum {
    NUM_CODE(0, "纯数字验证码"),
    BLEND_CODE(1, "数字字母混合验证码")
    ;

    private Integer code;

    private String description;

    VerifyCodeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
