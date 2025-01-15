package az.rufat.ioc_container.repositories;

import az.rufat.ioc_container.entities.User;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Getter
public class UserRepository {
    private final List<User> users = new ArrayList<>(List.of(
            new User(1, "Rufat", "12345"),
            new User(2, "Nasir", "12345")
    ));

    public void save(User user){
        users.add(user);
    }

}
