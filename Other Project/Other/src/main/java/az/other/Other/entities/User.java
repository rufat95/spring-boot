package az.other.Other.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;

    private String username;
    private String password;
}
