package pro.sky.telegrambot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.User;
import pro.sky.telegrambot.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping
    public ResponseEntity<User> editUser(@RequestBody User user) {
        User foundUser = userService.editUser(user);
        if (foundUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundUser);
    }

    @GetMapping
    public ResponseEntity findUser(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ResponseEntity.ok(userService.findUserById(id));
        }
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
