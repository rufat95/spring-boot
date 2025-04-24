package az.candyshop.CandyShop.repositories;

import az.candyshop.CandyShop.entities.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, Long> {
    Otp findByOtp(Integer otp);
}
