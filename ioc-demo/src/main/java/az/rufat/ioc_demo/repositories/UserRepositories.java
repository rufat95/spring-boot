package az.rufat.ioc_demo.repositories;

import az.rufat.ioc_demo.entities.User;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Getter
public class UserRepositories {
    private final List<User> users = new ArrayList<>(List.of(
            new User(1, "Rufat", "123456"),
            new User(2, "Nasir", "123456"),
            new User(3, "Ali", "123456")
    ));

//    public void save(User user){
//        this.users.add(user);
//    }
}
