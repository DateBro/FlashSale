package com.imooc.miaosha.converter;

import com.imooc.miaosha.dto.ProductDTO;
import com.imooc.miaosha.enums.ResultEnum;
import com.imooc.miaosha.exception.MiaoshaException;
import com.imooc.miaosha.viewobject.ProductVO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author DateBro
 * @Date 2021/2/17 20:24
 */
public class ProductDTO2ProductVOConverter {
    public static ProductVO convert(ProductDTO productDTO) {
        if (productDTO == null) {
            throw new MiaoshaException(ResultEnum.PARAMETER_VALIDATION_ERROR);
        }
        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(productDTO, productVO);
        return productVO;
    }

    public static List<ProductVO> convert(List<ProductDTO> productDTOList) {
        if (productDTOList == null || productDTOList.size()==0) {
            throw new MiaoshaException(ResultEnum.PARAMETER_VALIDATION_ERROR);
        }
        List<ProductVO> productVOList = productDTOList.stream().map(productDTO -> {
            return convert(productDTO);
        }).collect(Collectors.toList());
        return productVOList;
    }
}
