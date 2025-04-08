package com.ecommerce.Ecommerce.controllers;

import com.ecommerce.Ecommerce.requests.ProductCreateRequest;
import com.ecommerce.Ecommerce.resposes.ProductResponse;
import com.ecommerce.Ecommerce.result.success.SuccessDataResult;
import com.ecommerce.Ecommerce.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public SuccessDataResult<ProductResponse> createProduct(
            @RequestBody @Valid ProductCreateRequest productCreateRequest){
        return productService.createProduct(productCreateRequest);
    }

}
