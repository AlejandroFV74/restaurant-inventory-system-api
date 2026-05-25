package com.aafv.restaurantapi.services.impl;

import com.aafv.restaurantapi.common.mapper.ProductMapper;
import com.aafv.restaurantapi.dto.request.CreateProductRequest;
import com.aafv.restaurantapi.dto.request.UpdateProductRequest;
import com.aafv.restaurantapi.dto.response.ProductResponse;
import com.aafv.restaurantapi.exceptions.BusinessRuleException;
import com.aafv.restaurantapi.exceptions.ResourceNotFoundException;
import com.aafv.restaurantapi.model.Product;
import com.aafv.restaurantapi.model.enums.ProductCategoryEnum;
import com.aafv.restaurantapi.repository.ProductRepository;
import com.aafv.restaurantapi.services.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductResponse createProduct(CreateProductRequest productRequest){
        if(productRepository.existsByNameIgnoreCase(productRequest.getName())){
            throw new BusinessRuleException("A product with this name already exists");
        }

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
                        .orElseThrow(() -> new ResourceNotFoundException("Product didn't match any search"))
        );
    }

    @Override
    public List<ProductResponse> getProductsByCategoryAndAvailability(
            ProductCategoryEnum category,
            Boolean available
    ) {
        return productRepository.findByCategoryAndAvailable(category, available)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(UpdateProductRequest productRequest, long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product didn't match any search"));
        if (productRequest.getQuantity() == null) {
            throw new BusinessRuleException("Quantity is required");
        }

        if (productRequest.getQuantity() < 0) {
            throw new BusinessRuleException("Quantity cannot be less than 0");
        }

        Product updatedProduct = productMapper.toProductUpdate(productRequest, product);

        return productMapper.toDto(productRepository.save(updatedProduct));

    }

    @Override
    @Transactional
    public ProductResponse deleteProduct(long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product didn't match any search"));
                ;
        if (product.getCategory() == ProductCategoryEnum.INGREDIENT && Boolean.TRUE.equals(product.getAvailable())){
            throw new BusinessRuleException("Available ingredients cannot be deleted");

        }
        productRepository.deleteById(id);
        return productMapper.toDto(product);
    }
}
