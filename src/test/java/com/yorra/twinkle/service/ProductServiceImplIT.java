package com.yorra.twinkle.service;

import com.yorra.twinkle.repository.ProductRepository;
import com.yorra.twinkle.service.entities.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceImplIT {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ProductRepository productRepository;
}
