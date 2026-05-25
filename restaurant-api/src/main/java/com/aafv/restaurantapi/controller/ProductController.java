package com.aafv.restaurantapi.controller;

import com.aafv.restaurantapi.dto.request.CreateProductRequest;
import com.aafv.restaurantapi.dto.request.UpdateProductRequest;
import com.aafv.restaurantapi.dto.response.GeneralResponse;
import com.aafv.restaurantapi.dto.response.ProductResponse;
import com.aafv.restaurantapi.model.Product;
import com.aafv.restaurantapi.model.enums.ProductCategoryEnum;
import com.aafv.restaurantapi.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createProduct(@Valid @RequestBody CreateProductRequest request) {
        ProductResponse  productResponse = productService.createProduct(request);
        return buildResponse(
                "Product successfully created",
                HttpStatus.CREATED,
                productResponse

        );
    }

    @GetMapping("")
    public ResponseEntity<GeneralResponse> getAllProducts() {
        List<ProductResponse> productResponse = productService.getAllProducts();
        return buildResponse(
                "All products",
                HttpStatus.OK,
                productResponse
        );
    }

    @GetMapping("/{id]")
    public ResponseEntity<GeneralResponse> getProductById(@PathVariable Long id) {
        ProductResponse productResponse = productService.getProductById(id);
        return buildResponse(
                "Product identified:",
                HttpStatus.OK,
                productResponse
        );
    }

    @GetMapping("{category}&{available}")
    public ResponseEntity<GeneralResponse> getProductByCategory(@PathVariable ProductCategoryEnum category, @PathVariable Boolean available) {
        ProductResponse productResponse = productService.getProductsByCategoryAndAvailability(category, available);
        return buildResponse(
                "Products identified:",
                HttpStatus.OK,
                productResponse
        );

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GeneralResponse> updateProduct(@PathVariable long id, @RequestBody UpdateProductRequest request) {
        ProductResponse productResponse = productService.updateProduct( request , id);

        return buildResponse(
                "Product successfully updated",
                HttpStatus.OK,
                productResponse
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GeneralResponse> deleteProduct(@PathVariable long id) {
        ProductResponse productResponse = productService.deleteProduct(id);

        return buildResponse(
                "Product successfully deleted",
                HttpStatus.OK,
                productResponse
        );
    }



    public ResponseEntity<GeneralResponse> buildResponse(String message, HttpStatus status, Object data) {
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        return ResponseEntity
                .status(status)
                .body(GeneralResponse.builder()
                        .uri(uri)
                        .message(message)
                        .status(status.value())
                        .time(LocalDateTime.now())
                        .data(data)
                        .build()
                );
    }

}
