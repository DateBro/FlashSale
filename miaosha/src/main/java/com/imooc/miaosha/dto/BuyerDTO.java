package com.imooc.miaosha.dto;

import lombok.Data;

/**
 * @Author DateBro
 * @Date 2021/2/16 17:09
 * DTO，data transfer object，用来各层之间传输数据
 * CartDTO的属性可以参照api文档，文档中前端传来的数据有items数组，里面单个元素是productId和quantity
 */
@Data
public class BuyerDTO {
    private String telephone;

    private String otpCode;

    private String name;

    private Integer gender;

    private Integer age;

    private String password;
}
