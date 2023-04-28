package com.codegym.demo.service.impl;

import com.codegym.demo.dto.ProductDto;
import com.codegym.demo.entity.Product;
import com.codegym.demo.repository.ProductRepository;
import com.codegym.demo.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public Iterable<ProductDto> findAll() {
        Iterable<Product> entities =productRepository.findAll();
        return StreamSupport.stream(entities.spliterator(), true)
                .map(entity -> mapper.map(entity, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDto> findById(Integer id) {
        Product entity = productRepository.findById(id).orElse(null);
        return Optional.of(mapper.map(entity, ProductDto.class));
    }



    @Override
    public void save(ProductDto productDto) {
        Product product = mapper.map(productDto, Product.class);
        productRepository.save(product);
    }


}
