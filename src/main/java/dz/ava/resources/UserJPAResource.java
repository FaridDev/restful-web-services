package dz.ava.resources;

import dz.ava.domaine.Post;
import dz.ava.domaine.User;
import dz.ava.exception.UserNotFoundException;
import dz.ava.repositories.PostRepository;
import dz.ava.services.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserJPAResource {

    private final UserService userService;
    private final PostRepository postRepository;

    public UserJPAResource(@Qualifier("userServiceImpl") UserService userService, PostRepository postRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/jpa/users/{id}")
    public Resource<User> getUser(@PathVariable Integer id) {
        Optional<User> userOptional = Optional.ofNullable(userService.findOne(id));
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("id-" + id);
        }

        Resource<User> resource = new Resource<>(userOptional.get());
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        User savedUser = userService.saveUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> getAllUsers(@PathVariable Integer id) {
        Optional<User> userOptional = Optional.ofNullable(userService.findOne(id));
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("id-" + id);
        }
        return userOptional.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable Integer id, @RequestBody Post post){
        Optional<User> userOptional = Optional.ofNullable(userService.findOne(id));
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("id-" + id);
        }

        User user = userOptional.get();
        post.setUser(user);
        postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(location).build();

    }
}
