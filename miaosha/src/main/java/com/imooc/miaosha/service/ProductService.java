package com.imooc.miaosha.service;

import com.imooc.miaosha.dataobject.ProductInfo;
import com.imooc.miaosha.dto.OrderDTO;
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

    ProductDTO getProductDetailInCache(Integer productId);

    void decreaseStockInCache(OrderDTO orderDTO);

    void decreaseStockInDB(Integer productId, Integer productQuantity);

    void increaseSales(OrderDTO orderDTO);

    void increaseStock(Integer productId, Integer productQuantity2Increase);

    void publicStock(Integer productId);
}
