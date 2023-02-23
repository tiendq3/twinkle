package com.yorra.twinkle.controller;

import com.yorra.twinkle.model.dtos.ProductDTO;
import com.yorra.twinkle.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Page<ProductDTO>> getAll(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size,
                                                   @RequestParam(defaultValue = "name") String[] properties,
                                                   @RequestParam(defaultValue = "ASC") Sort.Direction sort) {
        log.info("GET ALL PRODUCT REQUEST");
        return ResponseEntity.ok(productService.getAllProduct(page, size, properties, sort));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("/management/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertProduct(@RequestBody @Valid ProductDTO productDTO) {
        log.info("INSERT NEW PRODUCT " + productDTO);
        productService.insertProduct(productDTO);
    }


    @PutMapping("management/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        log.info("UPDATE PRODUCT " + productDTO);
        productService.updateProduct(id, productDTO);
    }

    @DeleteMapping("/management/products")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@RequestParam Long[] ids) {
        productService.deleteProduct(ids);
    }
}
