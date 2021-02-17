package com.imooc.miaosha.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author DateBro
 * @Date 2021/2/16 15:30
 */
@Entity
@Data
public class BuyerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer buyerId;

    private String username;

    private Integer gender;

    private Integer age;

    private String telephone;

    private String registerMode;

    private String thirdPartyId;
}
