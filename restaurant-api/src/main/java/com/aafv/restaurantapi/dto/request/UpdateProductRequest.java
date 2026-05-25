package com.aafv.restaurantapi.dto.request;

import com.aafv.restaurantapi.model.enums.ProductCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private ProductCategoryEnum category;
}
