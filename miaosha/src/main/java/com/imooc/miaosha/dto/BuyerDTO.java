package com.imooc.miaosha.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author DateBro
 * @Date 2021/2/16 17:09
 * DTO，data transfer object，用来各层之间传输数据
 * BuyerDTO的属性可以参照api文档
 */
@Data
public class BuyerDTO implements Serializable {
    private static final long serialVersionUID = 8874085647279084088L;

    private Integer buyerId;

    private String telephone;

    private String otpCode;

    private String username;

    private Integer gender;

    private Integer age;

    private String encryptPassword;

    private String registerMode;
}
