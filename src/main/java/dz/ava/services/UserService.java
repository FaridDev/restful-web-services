package dz.ava.services;

import dz.ava.domaine.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User findOne(Integer id);
    User saveUser(User user);
    void deleteById(Integer id);
}
