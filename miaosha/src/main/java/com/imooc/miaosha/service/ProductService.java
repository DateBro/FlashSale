package com.imooc.miaosha.service;

import com.imooc.miaosha.dataobject.ProductInfo;
import com.imooc.miaosha.dto.ProductDTO;

import java.util.List;

/**
 * @Author DateBro
 * @Date 2021/2/17 17:49
 */
public interface ProductService {

    ProductDTO create(ProductDTO productDTO);

    List<ProductDTO> getProductList();

    ProductDTO getProductDetail(Integer productId);
}
