package com.imooc.miaosha.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author DateBro
 * @Date 2021/3/1 17:18
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "tx.sms")
public class TxCloudSmsConfig {

    private String secretId;

    private String secretKey;

    private String appId;

    private String templateId;

    private String smsSign;
}
