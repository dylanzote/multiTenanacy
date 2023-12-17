package com.zote.multitenancy.controller;

import com.zote.multitenancy.model.ProductRequest;
import com.zote.multitenancy.model.ProductResponse;
import com.zote.multitenancy.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;


    @PostMapping("create/product")
    @ResponseStatus(HttpStatus.CREATED)
    void createProduct(@RequestBody ProductRequest productRequest) {
        log.info("incoming create product request with data {}", productRequest);
        productService.createProduct(productRequest);
    }

    @GetMapping("getAllProducts")
    @ResponseStatus(HttpStatus.OK)
    List<ProductResponse> getAllProducts() {
        log.info("incoming getAllProduct request");
        return productService.getAllProducts();
    }
}
