package az.texnoera.Spring_Description.business.concretes;

import az.texnoera.Spring_Description.business.abstracts.BrandService;
import az.texnoera.Spring_Description.dataAccess.abstracts.BrandRepository;
import az.texnoera.Spring_Description.entitis.Brand;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BrandManager implements BrandService {
    private BrandRepository brandRepository;

    @Autowired
    public BrandManager(BrandRepository brandRepository){
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> getAll(){
        return brandRepository.findAll();
    }
}
