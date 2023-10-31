package com.example.service.impl;

import com.example.entity.Product;
import com.example.mapper.ProductMapper;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: yangxiao
 * @DATE 2023/10/26 4:39
 */
@Service
public class ProductImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> getProducts() {
        return productMapper.getProduct();
    }

    @Override
    public Boolean deleteProduct(Integer id) {
        Product product = productMapper.getProductByID(id);
        if (product == null) {
            return false;
        }
        return productMapper.deleteProduct(id) > 0;

    }

    @Override
    public Boolean updateProduct(Product product) {
        return productMapper.updateProduct(product) > 0;
    }

    @Override
    public Boolean addProduct(Product product) {
        return productMapper.addProduct(product) > 0;
    }
}
