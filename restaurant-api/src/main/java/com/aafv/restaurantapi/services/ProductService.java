package com.aafv.restaurantapi.services;

import com.aafv.restaurantapi.dto.request.CreateProductRequest;
import com.aafv.restaurantapi.dto.request.UpdateProductRequest;
import com.aafv.restaurantapi.dto.response.ProductResponse;
import com.aafv.restaurantapi.model.Product;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(CreateProductRequest product);
    List<ProductResponse> getAllProducts();
    Product getProductById(long id);
    ProductResponse updateProduct(UpdateProductRequest product, long id);
    ProductResponse deleteProduct(long id);
}
