package pro.sky.telegrambot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.Users;
import pro.sky.telegrambot.service.UsersService;


@RestController
@RequestMapping("/users")
public class UsersController {

    private final Logger logger = LoggerFactory.getLogger(UsersController.class);

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    // Добавление владельца
    @PostMapping("/addUser")
    public Users addUser(@RequestBody Users user) {
        return usersService.addUser(user);
    }

    // Изменение параметров владельца
    @PutMapping("/editUser")
    public ResponseEntity<Users> editUser(@RequestBody Users user) {
        Users foundUser = usersService.editUser(user);
        if (foundUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundUser);
    }

    // Удаление владельца
    @DeleteMapping("{id}")
    public ResponseEntity deleteUsers(@RequestBody Long id) {
        usersService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findUser")
    public ResponseEntity findUser(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ResponseEntity.ok(usersService.findUsersById(id));
        }
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @RequestMapping("/volrep/{id}")
    public void sendM(@PathVariable Long id) {
        usersService.sendM(id);
    }

}