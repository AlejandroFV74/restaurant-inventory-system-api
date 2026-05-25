package com.aafv.restaurantapi.services;

import com.aafv.restaurantapi.dto.request.CreateProductRequest;
import com.aafv.restaurantapi.dto.request.UpdateProductRequest;
import com.aafv.restaurantapi.dto.response.ProductResponse;
import com.aafv.restaurantapi.model.Product;
import com.aafv.restaurantapi.model.enums.ProductCategoryEnum;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(CreateProductRequest product);
    List<ProductResponse> getAllProducts();
    ProductResponse getProductById(long id);
    ProductResponse updateProduct(UpdateProductRequest product, long id);
    ProductResponse deleteProduct(long id);
    List<ProductResponse> getProductsByCategoryAndAvailability(
            ProductCategoryEnum category,
            Boolean available
    );
}
