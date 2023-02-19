package com.yorra.twinkle.service.entities;

import com.yorra.twinkle.exception.NotFoundException;
import com.yorra.twinkle.model.dtos.ProductDTO;
import com.yorra.twinkle.model.entities.Product;
import com.yorra.twinkle.repository.ProductRepository;
import com.yorra.twinkle.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Data
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    @Override
    public Page<ProductDTO> getAllProduct(int pageNumber, int sizePage, String[] properties, Sort.Direction sort) {
        Pageable pageable = PageRequest.of(pageNumber, sizePage, Sort.Direction.ASC, properties);
        if (sort.isDescending()) {
            pageable = PageRequest.of(pageNumber, sizePage, Sort.Direction.DESC, properties);
        }
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(product -> modelMapper.map(product, ProductDTO.class));
    }

    @Override
    public Page<ProductDTO> search(String key, int pageNumber, int sizePage, String[] properties, Sort.Direction sort) {
        return null;
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) throw new NotFoundException("Not found product by " + id);
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public void insertProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        product.setRating((double) 0);
        product.setIsAvailable(false);
        product.setCreatedAt(new Date().toInstant());
        product.setUpdatedAt(new Date().toInstant());
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) throw new NotFoundException("not found product by " + id);
        product = modelMapper.map(productDTO, Product.class);
        product.setUpdatedAt(new Date().toInstant());
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long[] id) {

    }
}
