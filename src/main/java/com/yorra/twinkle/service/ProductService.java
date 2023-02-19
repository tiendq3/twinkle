package com.yorra.twinkle.service;

import com.yorra.twinkle.model.dtos.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface ProductService {
    Page<ProductDTO> getAllProduct(int pageNumber, int sizePage, String[] properties, Sort.Direction sort);

    Page<ProductDTO> search(String key, int pageNumber, int sizePage, String[] properties, Sort.Direction sort);

    ProductDTO getProductById(Long id);

    void insertProduct(ProductDTO productDTO);

    void updateProduct(Long id, ProductDTO productDTO);

    void deleteProduct(Long[] id);
}
