package com.imooc.miaosha.viewobject;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author DateBro
 * @Date 2021/2/16 17:17
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 8886566500050452704L;

    private Integer code;

    private String msg;

    private T data;
}
