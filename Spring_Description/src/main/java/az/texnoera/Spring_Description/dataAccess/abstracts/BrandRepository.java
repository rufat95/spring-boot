package az.texnoera.Spring_Description.dataAccess.abstracts;


import az.texnoera.Spring_Description.entitis.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
