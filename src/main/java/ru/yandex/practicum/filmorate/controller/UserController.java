package ru.yandex.practicum.filmorate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.services.UserService;

import java.util.Collection;

// класс контроллер для обработки пользователей
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // метод получения всех пользователей
    @GetMapping("/users")
    public Collection<User> getUser() {
        return userService.getUserMap();
    }

    // метод добавления пользователей
    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    //метод обновления пользователей
    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        return userService.updateUsers(user);
    }

    //метод добавления в друзья
    @PutMapping("/users/{id}/friends/{friendId}")
    public void addFriend(@PathVariable int id, @PathVariable int friendId) {
        userService.addFriends(id, friendId);
    }

    //метод удаления из друзей
    @DeleteMapping("/users/{id}/friends/{friendId}")
    public void deleteFriend(@PathVariable int id, @PathVariable int friendId) {
        userService.deleteFriends(id, friendId);
    }

    //метод возвращающий список друзей по id
    @GetMapping("/users/{id}/friends")
    public Collection<User> getFriend(@PathVariable int id) {
        return userService.getFriends(id);
    }

    //метод возвращающий друзей друзей (масло масляное)
    @GetMapping("/users/{id}/friends/common/{otherId}")
    public Collection<User> getFriendOfFriend(@PathVariable int id, @PathVariable int otherId) {
        return userService.getFriendOfFriends(id, otherId);

    }

    //метод возвращающий пользователя по id
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

}
