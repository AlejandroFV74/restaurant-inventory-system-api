package com.aafv.restaurantapi.common.mapper;

import com.aafv.restaurantapi.dto.request.CreateProductRequest;
import com.aafv.restaurantapi.dto.request.UpdateProductRequest;
import com.aafv.restaurantapi.dto.response.ProductResponse;
import com.aafv.restaurantapi.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProductCreate(CreateProductRequest createProductRequest) {
        return Product.builder()
                .name(createProductRequest.getName())
                .price(createProductRequest.getPrice())
                .available(createProductRequest.getAvailable())
                .description(createProductRequest.getDescription())
                .category(createProductRequest.getCategory())
                .build();
    }

    public Product toProductUpdate(UpdateProductRequest updateProductRequest, Long id) {
        return Product.builder()
                .id(id)
                .name(updateProductRequest.getName())
                .description(updateProductRequest.getDescription())
                .quantity(updateProductRequest.getQuantity())
                .price(updateProductRequest.getPrice())
                .category(updateProductRequest.getCategory())
                .build();
    }

    public ProductResponse toDto(Product product) {
        return ProductResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .available(product.getAvailable())
                .price(product.getPrice())
                .category(product.getCategory())
                .build();

    }
}
