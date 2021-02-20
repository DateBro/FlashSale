package com.imooc.miaosha.service;

import com.imooc.miaosha.dto.PromoDTO;

/**
 * @Author DateBro
 * @Date 2021/2/18 15:41
 */
public interface PromoService {
    PromoDTO getPromoByProductId(Integer productId);

    PromoDTO publishPromo(Integer promoId);
}
