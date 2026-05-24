package com.aafv.restaurantapi.services.impl;

import com.aafv.restaurantapi.repository.ProductRepository;
import com.aafv.restaurantapi.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends ProductService {
    private final ProductRepository productRepository;

    @Override

}
