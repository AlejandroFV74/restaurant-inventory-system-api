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
                .available(createProductRequest.getQuantity() > 0)
                .description(createProductRequest.getDescription())
                .quantity(createProductRequest.getQuantity())
                .category(createProductRequest.getCategory())
                .build();
    }

    public Product toProductUpdate(UpdateProductRequest request, Product product) {
                product.setName(request.getName());
                product.setDescription(request.getDescription());
                product.setPrice(request.getPrice());
                product.setQuantity(request.getQuantity());
                product.setCategory(request.getCategory());
                product.setAvailable(request.getQuantity() > 0);

        return product;
    }

    public ProductResponse toDto(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .available(product.getAvailable())
                .price(product.getPrice())
                .category(product.getCategory())
                .build();

    }
}
