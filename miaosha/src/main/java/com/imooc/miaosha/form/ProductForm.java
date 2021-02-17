package com.imooc.miaosha.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author DateBro
 * @Date 2021/2/17 18:24
 */
@Data
public class ProductForm {
    @NotEmpty(message = "商品名称必填")
    private String productName;

    @NotEmpty(message = "商品描述必填")
    private String productDescription;

    @NotNull(message = "商品单价必填")
    private BigDecimal productPrice;

    @NotNull(message = "商品库存必填")
    private Integer productStock;

    @NotEmpty(message = "商品图片链接必填")
    private String productIcon;
}
