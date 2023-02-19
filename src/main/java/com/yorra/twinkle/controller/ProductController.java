package com.yorra.twinkle.controller;

import com.yorra.twinkle.model.dtos.ProductDTO;
import com.yorra.twinkle.service.ProductService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// Todo hhhhhh
import javax.validation.Valid;

@RestController
@Data
@RequestMapping("api/v1/")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<ProductDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size,
                                                   @RequestParam(defaultValue = "name") String[] properties,
                                                   @RequestParam(defaultValue = "ASC") Sort.Direction sort) {
        return ResponseEntity.ok(productService.getAllProduct(page, size, properties, sort));
    }

    @PostMapping("/management/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertProduct(@RequestBody @Valid ProductDTO productDTO) {
        productService.insertProduct(productDTO);
    }

}
