package az.candyshop.CandyShop.services;

import az.candyshop.CandyShop.entities.Product;
import az.candyshop.CandyShop.enums.StatusCode;
import az.candyshop.CandyShop.mappers.ProductMappers;
import az.candyshop.CandyShop.repositories.ProductRepository;
import az.candyshop.CandyShop.requests.ProductRequests.ProductCreateRequest;
import az.candyshop.CandyShop.responses.ProductResponses.ProductCreateResponse;
import az.candyshop.CandyShop.responses.ProductResponses.ProductResponse;
import az.candyshop.CandyShop.result.PaginationResponse;
import az.candyshop.CandyShop.result.exception.BaseException;
import az.candyshop.CandyShop.result.success.SuccessDataResult;
import az.candyshop.CandyShop.result.success.SuccessPageDataset;
import az.candyshop.CandyShop.result.success.SuccessResult;
import az.candyshop.CandyShop.utils.ImageUploadValidations;
import az.candyshop.CandyShop.utils.VideoUploadValidations;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMappers productMappers;
    private final ImageUploadValidations imageUploadValidations;
    private final VideoUploadValidations videoUploadValidations;

    // Post api for create new product
    public SuccessDataResult<ProductCreateResponse> createProduct(
           @Valid ProductCreateRequest productCreateRequest){
        Product product = productRepository.findByName(productCreateRequest.getName());
        if (product != null){
            throw new BaseException(HttpStatus.BAD_REQUEST, StatusCode.PRODUCT_UNIQUE);
        }

        if(productCreateRequest.getSellingPrice().compareTo(productCreateRequest.getOriginPrice()) < 0){
            throw new BaseException(HttpStatus.BAD_REQUEST, StatusCode.PRODUCT_PRICE);
        }

        return new SuccessDataResult<>("Product created successfully.",
                productMappers.changeProductToProductCreateResponse(
                        productRepository.save(
                                productMappers.changeProductCreateRequestToProduct(
                                        productCreateRequest))));
    }

    // Put api for created product upload picture and video
    public SuccessDataResult<Product> uploadProductFiles(
            String name, MultipartFile picture, MultipartFile video) throws IOException {
        Product product = productRepository.findByName(name);
        if(product == null){
            throw new BaseException(HttpStatus.NOT_FOUND, StatusCode.PRODUCT_NOT_FOUND);
        }
        imageUploadValidations.productImageUpload(picture, product);
        videoUploadValidations.productVideoUpload(video, product);
        return new SuccessDataResult<>("Files of product upload successfully.",
                productRepository.save(product));
    }

    // Get api for fetch all product with pageable
    public SuccessPageDataset<List<ProductResponse>> getAllProductList(
            int page, int size, String sortBy, String sortDirection) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<Product> productsPage = productRepository.findAll(pageable);
        List<ProductResponse> productResponses =
                productMappers.changePageProductToListProductResponse(productsPage);
        PaginationResponse pagination = new PaginationResponse(
                productsPage.getNumber(),
                productsPage.getSize(),
                productsPage.getTotalElements(),
                productsPage.getTotalPages()
        );
        return new SuccessPageDataset<>(
                true,
                "Products fetched successfully",
                productResponses,
                pagination
        );
    }

    // Get api for fetch one product with name
    public SuccessDataResult<ProductResponse> getOneProductByName(String name) {
        return new SuccessDataResult<>("Product fetched successfully",
                productMappers.changeProductToProductResponse(productRepository.findByName(name)));
    }

    // Put api for increasing product stock
    public SuccessDataResult<ProductResponse> increaseProductStock(
            String name, Integer increaseStock) {
        Product product = productRepository.findByName(name);
        if (product == null){
            throw new BaseException(HttpStatus.NOT_FOUND, StatusCode.PRODUCT_NOT_FOUND);
        }

        Integer resultStock = product.getStock() + increaseStock;
        product.setStock(resultStock);
        return new SuccessDataResult<>("Product stock are increased successfully",
                productMappers.changeProductToProductResponse(productRepository.save(product)));
    }

    public SuccessResult deleteProduct(String name) {
        Product product = productRepository.findByName(name);
        if (product == null){
            throw new BaseException(HttpStatus.NOT_FOUND, StatusCode.PRODUCT_NOT_FOUND);
        }
        productRepository.delete(product);
        return new SuccessResult("Product deleted successfully.");
    }
}


