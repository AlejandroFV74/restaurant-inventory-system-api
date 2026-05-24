package com.aafv.restaurantapi.dto.request;

import com.aafv.restaurantapi.model.enums.ProductCategoryEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    @NotBlank(message = "Product name cannot be empty")
    private String name;

    private String description;

    @Positive(message = "Price must be a positive number")
    private BigDecimal price;

    private ProductCategoryEnum category;

    @PositiveOrZero(message = "Quantity cannot be negative")
    private Integer quantity;

    @NotBlank
    private Boolean available;
}
