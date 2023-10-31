package com.example.service;

import com.example.entity.Product;

import java.util.List;

/**
 * @author: yangxiao
 * @DATE 2023/10/26 4:28
 */
public interface ProductService {

    List<Product> getProducts();

    Boolean deleteProduct(Integer id);

    Boolean updateProduct(Product product);

    Boolean addProduct(Product product);
}
