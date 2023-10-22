package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;


/**
 * контракт для реализации класса InMemoryUserStorage
 */
public interface UserStorage {
    /**
     * метод возвращающий пользователя по id
     */
    User getUserById(int id);

    /**
     * метод возвращающий список пользователей
     */
    List<User> getUserMap();

    /**
     * метод добавления пользователей
     */
    User createUser(User user);

    /**
     * метод обновления пользователей
     */
    User updateUsers(User user);
}
