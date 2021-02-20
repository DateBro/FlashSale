package com.imooc.miaosha.controller;

import com.imooc.miaosha.dto.PromoDTO;
import com.imooc.miaosha.service.Impl.PromoServiceImpl;
import com.imooc.miaosha.utils.ResultVOUtil;
import com.imooc.miaosha.viewobject.PromoVO;
import com.imooc.miaosha.viewobject.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author DateBro
 * @Date 2021/2/20 20:43
 */
@RestController
@RequestMapping("/promo")
@Slf4j
public class PromotionController {

    @Autowired
    private PromoServiceImpl promoService;

    @GetMapping("/publishPromo")
    public ResultVO publishPromo(@RequestParam(value = "promoId", required = true) Integer promoId) {
        PromoDTO promoDTO = promoService.publishPromo(promoId);
        PromoVO promoVO = new PromoVO();
        BeanUtils.copyProperties(promoDTO, promoVO);
        return ResultVOUtil.success(promoVO);
    }
}
