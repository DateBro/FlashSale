package com.imooc.miaosha.service.Impl;

import com.imooc.miaosha.repository.SequenceInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author DateBro
 * @Date 2021/2/18 11:43
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class SequenceServiceImplTest {

    @Autowired
    private SequenceServiceImpl sequenceService;

    @Test
    void genUniqueOrderId() {
        String uniqueId = sequenceService.genUniqueOrderId();
        assertNotNull(uniqueId);
        log.info("生成的id为：" + uniqueId);
    }
}