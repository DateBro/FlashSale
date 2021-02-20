package com.imooc.miaosha.converter;

import com.imooc.miaosha.dataobject.PromoInfo;
import com.imooc.miaosha.dto.PromoDTO;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;

/**
 * @Author DateBro
 * @Date 2021/2/20 20:58
 */
public class PromoInfo2PromoDTOConverter {
    public static PromoDTO convert(PromoInfo promoInfo) {
        PromoDTO promoDTO = new PromoDTO();
        BeanUtils.copyProperties(promoInfo, promoDTO);
        // 判断当前时间是否秒杀活动即将开始或正在进行
        DateTime promoStartTime = new DateTime(promoInfo.getPromoStartTime());
        DateTime promoEndTime = new DateTime(promoInfo.getPromoEndTime());
        if (promoStartTime.isAfterNow()) {
            promoDTO.setPromoStatus(1);
        } else if (promoEndTime.isBeforeNow()) {
            promoDTO.setPromoStatus(3);
        } else {
            promoDTO.setPromoStatus(2);
        }
        return promoDTO;
    }
}
