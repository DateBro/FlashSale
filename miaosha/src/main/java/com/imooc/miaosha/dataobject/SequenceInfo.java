package com.imooc.miaosha.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author DateBro
 * @Date 2021/2/18 11:25
 */
@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class SequenceInfo {
    @Id
    private String name;

    private Integer currentValue;

    private Integer step;
}
