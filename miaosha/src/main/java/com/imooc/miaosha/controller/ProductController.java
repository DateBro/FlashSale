package com.imooc.miaosha.controller;

import com.imooc.miaosha.converter.ProductForm2ProductDTOConverter;
import com.imooc.miaosha.dto.ProductDTO;
import com.imooc.miaosha.enums.ResultEnum;
import com.imooc.miaosha.exception.MiaoshaException;
import com.imooc.miaosha.form.ProductForm;
import com.imooc.miaosha.service.Impl.ProductServiceImpl;
import com.imooc.miaosha.utils.ResultVOUtil;
import com.imooc.miaosha.viewobject.ProductVO;
import com.imooc.miaosha.viewobject.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
