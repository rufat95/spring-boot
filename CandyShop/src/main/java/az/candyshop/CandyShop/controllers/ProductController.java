package az.candyshop.CandyShop.controllers;

import az.candyshop.CandyShop.entities.Product;
import az.candyshop.CandyShop.requests.ProductRequests.ProductCreateRequest;
import az.candyshop.CandyShop.responses.ProductResponses.ProductCreateResponse;
import az.candyshop.CandyShop.responses.ProductResponses.ProductResponse;
import az.candyshop.CandyShop.result.success.SuccessDataResult;
import az.candyshop.CandyShop.result.success.SuccessPageDataset;
import az.candyshop.CandyShop.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping(value = "/createProduct")
    public SuccessDataResult<ProductCreateResponse> createProduct(
            @RequestBody @Valid ProductCreateRequest productCreateRequest){

        return productService.createProduct(productCreateRequest);
    }

    @PutMapping(value = "/uploadFiles",
                consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SuccessDataResult<Product> uploadProductFiles(
            @RequestParam String name,
            @RequestPart MultipartFile picture,
            @RequestPart MultipartFile video) throws IOException {
        return productService.uploadProductFiles(name, picture, video);
    }

    @GetMapping("all_products")
    public SuccessPageDataset<List<ProductResponse>> getAllProductList(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDirection) {

            return productService.getAllProductList(page, size, sortBy, sortDirection);
    }

    @GetMapping("/{name}")
    public SuccessDataResult<ProductResponse> getOneProductByName(
            @PathVariable String name){
        return productService.getOneProductByName(name);
    }

    @PutMapping("/add_product")
    public SuccessDataResult<ProductResponse> increaseProductStock(
            @RequestParam String name,
            @RequestParam Integer increaseStock
    ){
        return productService.increaseProductStock(name, increaseStock);
    }
}
