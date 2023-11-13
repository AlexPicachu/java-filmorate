package ru.yandex.practicum.filmorate.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.UserDbStorage;
import ru.yandex.practicum.filmorate.storage.interfaces.UserStorage;
import ru.yandex.practicum.filmorate.validationException.NotFoundException;
import ru.yandex.practicum.filmorate.validationException.ValidationException;

import java.time.LocalDate;
import java.util.Collection;


import static org.junit.jupiter.api.Assertions.*;


/**
 * тестовый класс для проверки работы класса UserController
 */
class UserServiceTest {
    User user;
    UserController userController;
    UserDbStorage userDbStorage;
    JdbcTemplate jdbcTemplate;
    UserService userService;
    UserStorage userStorage;

    @BeforeEach
    public void launchBefore() {
        user = new User("qwe@mail.ru", "qwerty", "Ivan",
                LocalDate.of(1990, 10, 3));
        userStorage = new InMemoryUserStorage();
        jdbcTemplate = new JdbcTemplate();
        userDbStorage = new UserDbStorage(jdbcTemplate);
        userService = new UserService(userStorage, userDbStorage);
        userController = new UserController(userService);
    }

    /**
     * проверяем работу метода addUser
     */
    @Test
    public void addOfUserTest() {
        userController.addUser(user);
        Collection<User> userCollections = userController.getUser();
        assertEquals(1, userCollections.size(), "пользователь не добавлен");
    }


    /**
     * проверяем работу метода addUser при незаполненном getName
     */
    @Test
    public void addUserTestNameIsEmpty() {
        User user1 = new User("qwe@mail.ru", "qwerty",
                LocalDate.of(1990, 10, 3));
        String name = userController.addUser(user1).getName();
        assertEquals("qwerty", name, "ипя не проинициализировано логином");
    }

    /**
     * проверяем работу метода addUser при пустом Login
     */
    @Test
    public void addUserTestLoginIsEmpty() {
        User user1 = new User("qwe@mail.ru", " ", "Ivanov",
                LocalDate.of(1990, 10, 3));
        ValidationException ex = Assertions.assertThrows(ValidationException.class,
                () -> userController.addUser(user1));
        Assertions.assertEquals("Неверный login", ex.getMessage());
    }


    /**
     * проверяем работу метода updateUser
     */
    @Test
    public void updateUserTest() {
        userController.addUser(user);
        user = new User(1, "qwe@mail.ru", "qwerty", "Ivanov_Ivan",
                LocalDate.of(1990, 10, 3));
        String name = userController.updateUser(user).getName();
        assertEquals("Ivanov_Ivan", name, "иям не обновлено");
    }

    /**
     * проверяем работу метода updateUser при негативном сценарии
     */
    @Test
    public void updateUserTestNegative() {
        userController.addUser(user);
        user = new User(1000, "qwe@mail.ru", "qwerty", "Ivanov_Ivan",
                LocalDate.of(1990, 10, 3));
        NotFoundException ex = Assertions.assertThrows(NotFoundException.class,
                () -> userController.updateUser(user));
        Assertions.assertEquals("пользователя с id = " + user.getId() + " не существует",
                ex.getMessage());
    }

}