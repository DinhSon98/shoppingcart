package com.codegym.demo.service;

import com.codegym.demo.dto.ProductDto;
import com.codegym.demo.dto.ShoppingCartDto;
import com.codegym.demo.entity.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.Optional;
public interface ShoppingCartService {
    Iterable<ShoppingCartDto> findAll();

    Optional<ShoppingCartDto> findById(Integer id);
//    Optional<ShoppingCartDto> findByProductId(Integer id);
    Iterable<ShoppingCartDto> findByIsDeleted();

    void save(ShoppingCartDto shoppingCart);

    void remove(Integer id);
//    Optional<ProductDto> findByName(String name);
}
