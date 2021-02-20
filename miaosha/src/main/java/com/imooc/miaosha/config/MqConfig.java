package com.imooc.miaosha.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author DateBro
 * @Date 2021/2/20 21:27
 */
@Data
@Component
@ConfigurationProperties(prefix = "mq", ignoreUnknownFields = true)
public class MqConfig {

    private Map<String, String> nameServer;

    private String topicName;

}
