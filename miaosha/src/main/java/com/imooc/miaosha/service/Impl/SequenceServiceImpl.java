package com.imooc.miaosha.service.Impl;

import com.imooc.miaosha.dataobject.SequenceInfo;
import com.imooc.miaosha.repository.SequenceInfoRepository;
import com.imooc.miaosha.service.SequenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author DateBro
 * @Date 2021/2/18 11:39
 */
@Service
@Slf4j
public class SequenceServiceImpl implements SequenceService {

    @Autowired
    private SequenceInfoRepository sequenceInfoRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String genUniqueOrderId() {
        //订单号有16位
        StringBuilder stringBuilder = new StringBuilder();
        //前8位为时间信息，年月日
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");
        stringBuilder.append(nowDate);

        //中间6位为自增序列
        //获取当前sequence
        int sequence = 0;
        SequenceInfo sequenceInfo = sequenceInfoRepository.getOne("orderInfo");
        sequence = sequenceInfo.getCurrentValue();
        sequenceInfo.setCurrentValue(sequenceInfo.getCurrentValue() + sequenceInfo.getStep());
        sequenceInfoRepository.save(sequenceInfo);

        String sequenceStr = String.valueOf(sequence);
        for (int i = 0; i < 6 - sequenceStr.length(); i++)
            stringBuilder.append(0);
        stringBuilder.append(sequenceStr);

        //最后2位为分库分表位,暂时写死
        stringBuilder.append("00");

        return stringBuilder.toString();
    }
}
