package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.services.UserService;
import java.util.Collection;

@RestController
public class UserController {
    UserService userService = new UserService();

    @GetMapping("/users")
    public Collection<User> getUser() {
        return userService.getUserMap().values();
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        return userService.updateUsers(user);
    }
}
