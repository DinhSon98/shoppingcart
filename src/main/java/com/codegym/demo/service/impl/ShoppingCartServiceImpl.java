package com.codegym.demo.service.impl;

import com.codegym.demo.dto.ProductDto;
import com.codegym.demo.dto.ShoppingCartDto;
import com.codegym.demo.entity.Product;
import com.codegym.demo.entity.ShoppingCart;
import com.codegym.demo.repository.ShoppingCartRepository;
import com.codegym.demo.service.ShoppingCartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository repository;
    private final ModelMapper mapper;

    public ShoppingCartServiceImpl(ShoppingCartRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Iterable<ShoppingCartDto> findAll() {
        Iterable<ShoppingCart> entities =repository.findAll();
        return StreamSupport.stream(entities.spliterator(), true)
                .map(entity -> mapper.map(entity, ShoppingCartDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ShoppingCartDto> findById(Integer id) {
        ShoppingCart entity = repository.findById(id).orElse(null);
        return Optional.of(mapper.map(entity, ShoppingCartDto.class));
    }

//    @Override
//    public Optional<ShoppingCartDto> findByProductId(Integer id) {
//        ShoppingCart entity = repository.findShoppingCartByProductId(id).orElse(null);
//        return Optional.of(mapper.map(entity, ShoppingCartDto.class));
//    }

    @Override
    public Iterable<ShoppingCartDto> findByIsDeleted() {
        Iterable<ShoppingCart> entities =repository.findShoppingCartByIs_Deleted();
        return StreamSupport.stream(entities.spliterator(), true)
                .map(entity -> mapper.map(entity, ShoppingCartDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void save(ShoppingCartDto shoppingCartDto) {
        ShoppingCart shoppingCart = mapper.map(shoppingCartDto, ShoppingCart.class);
        repository.save(shoppingCart);
    }

    @Override
    public void remove(Integer id) {
        repository.deleteById(id);
    }

//    @Override
//    public Optional<ProductDto> findByName(String name) {
//        return repository.findByName(name);
//    }
}
