package com.imooc.miaosha.repository;

import com.imooc.miaosha.dataobject.SequenceInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author DateBro
 * @Date 2021/2/18 11:27
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class SequenceInfoRepositoryTest {

    @Autowired
    private SequenceInfoRepository sequenceInfoRepository;

    @Test
    void save() {
        SequenceInfo sequenceInfo = new SequenceInfo();
        sequenceInfo.setName("orderInfo");
        sequenceInfo.setCurrentValue(0);
        sequenceInfo.setStep(1);
        SequenceInfo result = sequenceInfoRepository.save(sequenceInfo);
        assertNotNull(result);
    }

}