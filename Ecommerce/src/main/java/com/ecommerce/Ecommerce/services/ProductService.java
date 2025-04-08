package com.ecommerce.Ecommerce.services;

import com.ecommerce.Ecommerce.entities.Product;
import com.ecommerce.Ecommerce.mappers.ProductMapper;
import com.ecommerce.Ecommerce.repositories.ProductRepository;
import com.ecommerce.Ecommerce.requests.ProductCreateRequest;
import com.ecommerce.Ecommerce.resposes.ProductResponse;
import com.ecommerce.Ecommerce.result.success.SuccessDataResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public SuccessDataResult<ProductResponse> createProduct(
            @Valid ProductCreateRequest productCreateRequest) {
        return new SuccessDataResult<>("Your product created successfully",
                productMapper.changeProduct(
                        productRepository.save(
                                productMapper.craeteProduct(
                                        productCreateRequest))));
    }
}
