package com.aafv.restaurantapi.services;

import com.aafv.restaurantapi.model.Product;

import java.util.List;

public interface ProductService {
    void createProduct(Product product);
    List<Product> getProducts();
    Product getProductById(long id);
    void updateProduct(Product product);
    void deleteProduct(long id);
}
