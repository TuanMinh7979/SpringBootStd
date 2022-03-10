package com.mvc.controller;

import java.util.List;

import com.mvc.dto.ProductDto;
import com.mvc.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")

public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("{id}")
    public ProductDto getProduct(@PathVariable("id") Integer id) {
        return productService.getProduct(id);
    }

}
