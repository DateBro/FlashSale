package com.imooc.miaosha.repository;

import com.imooc.miaosha.dataobject.PromoInfo;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.text.ParseException;

/**
 * @Author DateBro
 * @Date 2021/2/18 14:40
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class PromoInfoRepositoryTest {

    @Autowired
    private PromoInfoRepository promoInfoRepository;

    @Test
    void save() throws ParseException {
        PromoInfo promoInfo = new PromoInfo();
        promoInfo.setPromoName("秒杀活动测试2");
        promoInfo.setProductId(2);
        promoInfo.setPromoProductPrice(new BigDecimal(100));
        DateTime promoStartTime = new DateTime("2021-02-18T15:30:00");
        promoInfo.setPromoStartTime(promoStartTime.toDate());
        DateTime promoEndTime = promoStartTime.plusDays(10);
        promoInfo.setPromoEndTime(promoEndTime.toDate());
        PromoInfo result = promoInfoRepository.save(promoInfo);
        Assertions.assertNotNull(result);
        log.info("插入的活动信息为：" + result);
    }

    @Test
    void findByProductId() {
        Integer productId = 1;
        PromoInfo promoInfo = promoInfoRepository.findByProductId(productId);
        Assertions.assertNotNull(promoInfo);
        log.info("取出的活动信息为：" + promoInfo);
    }
}