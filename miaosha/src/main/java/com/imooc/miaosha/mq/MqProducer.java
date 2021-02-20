package com.imooc.miaosha.mq;

import com.alibaba.fastjson.JSON;
import com.imooc.miaosha.config.MqConfig;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author DateBro
 * @Date 2021/2/20 21:24
 */
@Component
public class MqProducer {

    @Autowired
    private MqConfig mqConfig;

    private DefaultMQProducer producer;

    @PostConstruct
    void init() throws MQClientException {
        //做mq producer的初始化
        producer = new DefaultMQProducer("producer_group");
        producer.setNamesrvAddr(mqConfig.getNameServer().get("addr"));
        producer.start();
    }

    //同步库存扣减消息
    public boolean asyncReduceStock(Integer productId, Integer productQuantity) {
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("productId", productId);
        bodyMap.put("productQuantity", productQuantity);

        Message message = new Message(mqConfig.getTopicName(), "increase",
                JSON.toJSON(bodyMap).toString().getBytes(StandardCharsets.UTF_8));
        try {
            producer.send(message);
        } catch (MQClientException e) {
            e.printStackTrace();
            return false;
        } catch (RemotingException e) {
            e.printStackTrace();
            return false;
        } catch (MQBrokerException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
