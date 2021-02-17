package com.imooc.miaosha.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author DateBro
 * @Date 2021/2/17 11:50
 */
@Data
public class BuyerForm {
    @NotEmpty(message = "手机号必填")
    private String telephone;

    @NotEmpty(message = "短信验证码必填")
    private String otpCode;

    @NotEmpty(message = "用户名必填")
    private String username;

    @NotNull(message = "性别必填")
    private Integer gender;

    @NotNull(message = "年龄必填")
    private Integer age;

    @NotEmpty(message = "密码必填")
    private String password;
}
