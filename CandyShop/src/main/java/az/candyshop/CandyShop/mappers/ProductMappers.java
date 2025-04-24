package az.candyshop.CandyShop.mappers;

import az.candyshop.CandyShop.entities.Product;
import az.candyshop.CandyShop.requests.ProductRequests.ProductCreateRequest;
import az.candyshop.CandyShop.responses.ProductResponses.ProductCreateResponse;
import az.candyshop.CandyShop.responses.ProductResponses.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMappers {
    ProductCreateResponse changeProductToProductCreateResponse(Product product);
    Product changeProductCreateRequestToProduct(ProductCreateRequest productCreateRequest);
    List<ProductResponse> changePageProductToListProductResponse(Page<Product> products);
    ProductResponse changeProductToProductResponse(Product product);
}
