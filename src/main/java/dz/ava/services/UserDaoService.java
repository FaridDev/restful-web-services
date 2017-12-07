package dz.ava.services;

import dz.ava.domaine.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserDaoService implements UserService{

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;

    static {
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Eve", new Date()));
        users.add(new User(3, "Jack", new Date()));
    }

    public List<User> getUsers() {
        return users;
    }

    public User findOne(Integer id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public User saveUser(User user) {
        if(user.getId() == null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }
}
