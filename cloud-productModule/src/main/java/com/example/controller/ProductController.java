package com.example.controller;

import com.example.entity.CommonResponse;
import com.example.entity.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: yangxiao
 * @DATE 2023/10/26 4:28
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ROLE_PRODUCT_ADMIN', 'ROLE_USER')")
    public CommonResponse getProduct() {
        List<Product> products = productService.getProducts();
        return new CommonResponse(2000, "成功", products);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAnyRole('ROLE_PRODUCT_ADMIN', 'ROLE_EDITOR')")
    public CommonResponse deleteProduct(@RequestParam(name = "id", required = true) Integer id) {
        Boolean flag = productService.deleteProduct(id);
        CommonResponse response = new CommonResponse(2000, "删除成功", null);
        if (!flag) {
            response.setMessage("删除失败");
        }
        return response;
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_PRODUCT_ADMIN', 'ROLE_EDITOR')")
    public CommonResponse updateProduct(Product product) {
        CommonResponse response = new CommonResponse(2000, "修改成功", null);
        if (product.getId() == null || ObjectUtils.isEmpty(product.getName())) {
            response.setMessage("参数不合法");
            return response;
        }
        Boolean flag = productService.updateProduct(product);
        if (!flag){
            response.setMessage("修改失败");
        }
        return response;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ROLE_PRODUCT_ADMIN', 'ROLE_EDITOR')")
    public CommonResponse addProduct(@RequestParam(name = "name", required = true) String name) {
        CommonResponse response = new CommonResponse(2000, "添加成功", null);
        if (ObjectUtils.isEmpty(name)) {
            response.setMessage("添加失败");
            return response;
        }
        Product product = new Product();
        product.setName(name);
        Boolean flag = productService.addProduct(product);
        if (!flag) {
            response.setMessage("添加失败");
        }
        return response;
    }
}
