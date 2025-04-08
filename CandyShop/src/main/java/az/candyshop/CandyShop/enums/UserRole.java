package az.candyshop.CandyShop.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_EMPLOYEE;


    @Override
    public String getAuthority() {
        return name();
    }
}
