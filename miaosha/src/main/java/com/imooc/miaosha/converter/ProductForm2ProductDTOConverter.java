package com.imooc.miaosha.converter;

import com.imooc.miaosha.dto.ProductDTO;
import com.imooc.miaosha.form.ProductForm;

/**
 * @Author DateBro
 * @Date 2021/2/17 18:35
 */
public class ProductForm2ProductDTOConverter {

    public static ProductDTO convert(ProductForm productForm) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setProductName(productForm.getProductName());
        productDTO.setProductDescription(productForm.getProductDescription());
        productDTO.setProductPrice(productForm.getProductPrice());
        productDTO.setProductIcon(productForm.getProductIcon());
        productDTO.setStock(productForm.getProductStock());

        return productDTO;
    }
}
