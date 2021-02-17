package com.imooc.miaosha.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author DateBro
 * @Date 2021/2/16 15:45
 */
@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class ProductStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stockId;

    private Integer stock;

    private Integer productId;
}
