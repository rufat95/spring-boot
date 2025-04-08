package com.ecommerce.Ecommerce.mappers;

import com.ecommerce.Ecommerce.entities.Product;
import com.ecommerce.Ecommerce.requests.ProductCreateRequest;
import com.ecommerce.Ecommerce.resposes.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductMapper {
    Product craeteProduct(ProductCreateRequest productCreateRequest);
    ProductResponse changeProduct(Product product);
}
