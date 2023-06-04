package ro.uaic.info.fiipractic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.info.fiipractic.DTO.UserDTO;
import ro.uaic.info.fiipractic.DTO.UserRegisterDTO;
import ro.uaic.info.fiipractic.entity.UserEntity;
import ro.uaic.info.fiipractic.service.UserService;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<String> register (@RequestBody UserRegisterDTO user)
    {
        userService.register(user);
        return new ResponseEntity<>("User inserted!",HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> findById (@PathParam("id") Optional<Integer> id, @PathParam("firstName") Optional<String> firstName, @PathParam("lastName") Optional<String> lastName)
    {
        List<UserDTO> users = userService.findByCriteria(id, firstName, lastName);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("{id1}/follow/{id2}")
    public ResponseEntity<String> follow (@PathVariable Integer id1, @PathVariable Integer id2)
    {
        userService.follow(id1,id2);
        return new ResponseEntity<>("User followed!", HttpStatus.OK);
    }

    @DeleteMapping("{id1}/unfollow/{id2}")
    public ResponseEntity<String> unfollow (@PathVariable Integer id1, @PathVariable Integer id2)
    {
        userService.unfollow(id1,id2);
        return new ResponseEntity<>("User unfollowed!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        userService.delete(id);
        return new ResponseEntity<>("User deleted!", HttpStatus.OK);
    }
}
