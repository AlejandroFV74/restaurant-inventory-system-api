package com.aafv.restaurantapi.repository;

import com.aafv.restaurantapi.model.Product;
import com.aafv.restaurantapi.model.enums.ProductCategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductById(long id);
    boolean existsByNameIgnoreCase(String name);
    List<Product> available(Boolean available);
    List<Product> findByCategoryAndAvailable(ProductCategoryEnum category, Boolean available);
}
