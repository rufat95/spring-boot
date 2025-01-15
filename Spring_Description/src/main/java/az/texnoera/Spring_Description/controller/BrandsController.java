package az.texnoera.Spring_Description.controller;

import az.texnoera.Spring_Description.business.abstracts.BrandService;
import az.texnoera.Spring_Description.entitis.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/brands")
public class BrandsController {
    private BrandService brandService;

    public BrandsController(BrandService brandService){
        this.brandService = brandService;
    }

    @GetMapping("/getAll")
    public List<Brand> getAll(){
        return brandService.getAll();
    }
}
