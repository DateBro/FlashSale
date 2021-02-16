package com.imooc.miaosha.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author DateBro
 * @Date 2021/2/16 15:35
 */
@Entity
@Data
public class BuyerPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String encryptPassword;

    private Integer buyerId;
}
