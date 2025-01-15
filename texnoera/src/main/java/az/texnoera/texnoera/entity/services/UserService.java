package az.texnoera.texnoera.entity.services;

import az.texnoera.texnoera.entity.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final List<User>  users = new ArrayList<>(List.of(
            new User(1,"Rufat",29),
            new User(2,"Murad",25),
            new User(3,"Nasir",32),
            new User(4,"Ali",31)
    ));

    public List<User> getUsers(){
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }

    public void deleteUser(int id){
      for (int i = 0 ; i< users.size(); i++){
          if (users.get(i).getId() == id){
              users.remove(i);
          }
      }
    }

    public void updateUser(int id, String name){
        users.forEach(user -> {
            if(user.getId()==id){
                user.setName(name);
            }
        });
    }
}

