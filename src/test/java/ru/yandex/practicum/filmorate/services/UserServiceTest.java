package ru.yandex.practicum.filmorate.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;
import ru.yandex.practicum.filmorate.validationException.NotFoundException;
import ru.yandex.practicum.filmorate.validationException.ValidationException;

import java.time.LocalDate;
import java.util.Collection;


import static org.junit.jupiter.api.Assertions.*;


//тестовый класс для проверки работы класса UserController
class UserServiceTest {
    User user;
    UserController userController;
    UserService userService;
    UserStorage userStorage;

    @BeforeEach
    public void launchBefore() {
        user = new User("qwe@mail.ru", "qwerty", "Ivan",
                LocalDate.of(1990, 10, 3));
        userStorage = new InMemoryUserStorage();
        userService = new UserService(userStorage);
        userController = new UserController(userService);
    }

    //проверяем работу метода addUser
    @Test
    public void addOfUserTest() {
        userController.addUser(user);
        Collection<User> userCollections = userController.getUser();
        assertEquals(1, userCollections.size(), "пользователь не добавлен");
    }

    //проверяем работу метода addUser при негативном сценарии
    @Test
    public void addUserTesNegative() {
        User user1 = new User("qwe@mail.ru", "qwerty", "Ivan",
                LocalDate.of(2025, 10, 3));
        ValidationException ex = Assertions.assertThrows(ValidationException.class,
                () -> userController.addUser(user1));
        Assertions.assertEquals("дата рождения находится в будущем", ex.getMessage());
    }

    //проверяем работу метода addUser при незаполненном getName
    @Test
    public void addUserTestNameIsEmpty() {
        User user1 = new User("qwe@mail.ru", "qwerty",
                LocalDate.of(1990, 10, 3));
        String name = userController.addUser(user1).getName();
        assertEquals("qwerty", name, "ипя не проинициализировано логином");
    }

    //проверяем работу метода addUser при пустом Login
    @Test
    public void addUserTestLoginIsEmpty() {
        User user1 = new User("qwe@mail.ru", " ", "Ivanov",
                LocalDate.of(1990, 10, 3));
        ValidationException ex = Assertions.assertThrows(ValidationException.class,
                () -> userController.addUser(user1));
        Assertions.assertEquals("логин пустой или содержит пробелы", ex.getMessage());
    }

    //проверяем работу метода при неправильно заполненном Email
    @Test
    public void addUserTestEmailException() {
        User user1 = new User("qwemail.ru", "qwertyuiop", "Ivanov",
                LocalDate.of(1990, 10, 3));
        ValidationException ex = Assertions.assertThrows(ValidationException.class,
                () -> userController.addUser(user1));
        Assertions.assertEquals("электронная почта не заполнена или не содержит @ " + user1.getEmail(),
                ex.getMessage());
    }

    //проверяем работу метода updateUser
    @Test
    public void updateUserTest() {
        userController.addUser(user);
        user = new User(1, "qwe@mail.ru", "qwerty", "Ivanov_Ivan",
                LocalDate.of(1990, 10, 3));
        String name = userController.updateUser(user).getName();
        assertEquals("Ivanov_Ivan", name, "иям не обновлено");
    }

    //проверяем работу метода updateUser при негативном сценарии
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

    // проверяем работу метода добавления и удаления из друзей
    @Test
    public void addFriendUserEndDelete() {
        User user1 = new User("qw@mail.ru", "asd", "zxc",
                LocalDate.of(2000, 1, 1));
        userController.addUser(user);
        userController.addUser(user1);
        userController.addFriend(user.getId(), user1.getId());
        Collection<User> collection = userController.getFriend(user.getId());
        assertFalse(collection.isEmpty());
        assertTrue(collection.contains(user1));
        userController.deleteFriend(user.getId(), user1.getId());
        Collection<User> collection1 = userController.getFriend(user.getId());
        assertTrue(collection1.isEmpty());
    }

    //проверяем работу метода возвращающего друзей/друзей
    @Test
    public void getFriendOfFriendsTest() {
        User user1 = new User("qw@mail.ru", "asd", "zxc",
                LocalDate.of(2000, 1, 1));
        User user2 = new User("zxc@mail.ru", "poi", "Aleg",
                LocalDate.of(2001, 12, 3));
        userController.addUser(user);
        userController.addUser(user1);
        userController.addUser(user2);
        userController.addFriend(user.getId(), user1.getId());
        userController.addFriend(user1.getId(), user2.getId());
        Collection<User> collectionFriendOfFriend =
                userController.getFriendOfFriend(user.getId(), user2.getId());
        assertTrue(collectionFriendOfFriend.contains(user1));

    }

}