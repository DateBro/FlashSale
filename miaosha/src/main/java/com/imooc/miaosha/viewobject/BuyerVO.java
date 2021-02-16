package com.imooc.miaosha.viewobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author DateBro
 * @Date 2021/2/16 17:13
 */
@Data
public class BuyerVO {
    @JsonProperty("buyerId")
    Integer buyerId;

    @JsonProperty("username")
    String username;

    @JsonProperty("gender")
    Integer gender;

    @JsonProperty("age")
    Integer age;

    @JsonProperty("telephone")
    String telephone;
}
