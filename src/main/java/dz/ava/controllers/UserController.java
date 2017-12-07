package dz.ava.controllers;

import dz.ava.domaine.User;
import dz.ava.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Integer id){
        return userService.findOne(id);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user){
        userService.saveUser(user);
    }

}