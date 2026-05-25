package com.aafv.restaurantapi.services.impl;

import com.aafv.restaurantapi.common.mapper.ProductMapper;
import com.aafv.restaurantapi.dto.request.CreateProductRequest;
import com.aafv.restaurantapi.dto.request.UpdateProductRequest;
import com.aafv.restaurantapi.dto.response.ProductResponse;
import com.aafv.restaurantapi.model.Product;
import com.aafv.restaurantapi.repository.ProductRepository;
import com.aafv.restaurantapi.services.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductResponse createProduct(CreateProductRequest productRequest){
        //validar nombre unico sin importar mayuscula o minuscula
        return productMapper.toDto(
                productRepository.save(productMapper.toProductCreate(productRequest))
        );
    }

    @Override
    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(long id){
        return productMapper.toDto(
                productRepository.findById(id)
                        .orElseThrow(() -> new ResourseNotFoundException("Product didn't match any search"))
        );
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(UpdateProductRequest productRequest, long id){
        this.getProductById(id);
        return productMapper.toDto(productRepository.save(productMapper.toProductUpdate(productRequest, id)));

    }

    @Override
    @Transactional
    public ProductResponse deleteProduct(long id){
        ProductResponse product = this.getProductById(id);
        productRepository.deleteById(id);
        return product;
    }
}
