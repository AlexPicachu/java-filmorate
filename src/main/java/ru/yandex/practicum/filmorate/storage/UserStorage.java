package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

//интерфейс пользователей
public interface UserStorage {
    User getUserById(int id);

    List<User> getUserMap();

    User createUser(User user);

    User updateUsers(User user);
}
