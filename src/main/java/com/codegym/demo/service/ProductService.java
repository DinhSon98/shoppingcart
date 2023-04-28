package com.codegym.demo.service;

import com.codegym.demo.dto.ProductDto;
import com.codegym.demo.dto.ShoppingCartDto;
import org.springframework.stereotype.Service;

import java.util.Optional;
public interface ProductService {
    Iterable<ProductDto> findAll();

    Optional<ProductDto> findById(Integer id);

    void save(ProductDto productDto);

}
