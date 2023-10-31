package com.example.mapper;

import com.example.ProductApplication;
import com.example.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: yangxiao
 * @DATE 2023/10/26 4:28
 */
@Mapper
public interface ProductMapper {
    /**
     * 获取产品
     * @return
     */
    List<Product> getProduct();

    /**
     * 通过id获取产品
     * @param id
     * @return
     */
    Product getProductByID(Integer id);


    /**
     * 删除产品
     * @param id
     * @return
     */
    Integer deleteProduct(Integer id);

    /**
     * 更改产品
     * @param product
     * @return
     */
    Integer updateProduct(Product product);

    /**
     * 添加产品
     * @param product
     * @return
     */
    Integer addProduct(Product product);
}
