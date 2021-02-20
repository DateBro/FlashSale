package com.imooc.miaosha.controller;

import com.imooc.miaosha.constant.LocalCacheConstant;
import com.imooc.miaosha.constant.RedisConstant;
import com.imooc.miaosha.converter.ProductDTO2ProductVOConverter;
import com.imooc.miaosha.converter.ProductForm2ProductDTOConverter;
import com.imooc.miaosha.dto.ProductDTO;
import com.imooc.miaosha.enums.ResultEnum;
import com.imooc.miaosha.exception.MiaoshaException;
import com.imooc.miaosha.form.ProductForm;
import com.imooc.miaosha.service.Impl.LocalCacheServiceImpl;
import com.imooc.miaosha.service.Impl.ProductServiceImpl;
import com.imooc.miaosha.utils.ResultVOUtil;
import com.imooc.miaosha.viewobject.ProductVO;
import com.imooc.miaosha.viewobject.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author DateBro
 * @Date 2021/2/17 17:26
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private LocalCacheServiceImpl localCacheService;

    @GetMapping("/list")
    public ResultVO list() {
        List<ProductDTO> productDTOList = productService.getProductList();
        List<ProductVO> productVOList = ProductDTO2ProductVOConverter.convert(productDTOList);
        return ResultVOUtil.success(productVOList);
    }

    @GetMapping("/productDetail")
    public ResultVO productDetail(@RequestParam(value = "productId", required = true) Integer productId) {
        // 先从本地guava缓存中取商品详情信息
        ProductDTO productDTO = (ProductDTO) localCacheService.get(String.format(LocalCacheConstant.PRODUCT_PREFIX, productId));

        if(productDTO==null) {
            productDTO = (ProductDTO) redisTemplate.opsForValue().get(String.format(RedisConstant.PRODUCT_DETAIL_PREFIX, productId));
            if (productDTO == null) {
                // 如果redis中没有缓存，访问service，并将数据保存到redis中
                productDTO = productService.getProductDetail(productId);
                redisTemplate.opsForValue().set(String.format(RedisConstant.PRODUCT_DETAIL_PREFIX, productId), productDTO, RedisConstant.PRODUCT_DETAIL_EXPIRE, TimeUnit.SECONDS);
            }
            // 将数据保存到guava本地缓存中
            localCacheService.set(String.format(LocalCacheConstant.PRODUCT_PREFIX, productId), productDTO);
        }

        ProductVO productVO = ProductDTO2ProductVOConverter.convert(productDTO);
        return ResultVOUtil.success(productVO);
    }

    @PostMapping("/create")
    public ResultVO create(@Valid ProductForm productForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("【创建商品】输入表单有误");
            throw new MiaoshaException(ResultEnum.PARAMETER_VALIDATION_ERROR);
        }

        ProductDTO productDTO = ProductForm2ProductDTOConverter.convert(productForm);
        ProductDTO result = productService.create(productDTO);

        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(result, productVO);

        return ResultVOUtil.success(productVO);
    }

}
