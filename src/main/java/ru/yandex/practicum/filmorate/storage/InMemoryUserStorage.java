package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.interfaces.UserStorage;
import ru.yandex.practicum.filmorate.validationException.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * класс хранилище пользователей
 */
@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage {
    private final Map<Integer, User> userMap = new HashMap<>();
    private int nextId = 1;


    /**
     * метод возвращающий пользователя по его id
     */
    @Override
    public User getUserById(int id) {
        if (!userMap.containsKey(id)) {
            throw new NotFoundException("Пользователя с таким id " + id + " не существует");
        }
        return userMap.get(id);
    }


    /**
     * метод возвращающий список пользователей
     */
    @Override
    public List<User> getUserMap() {
        return new ArrayList<>(userMap.values());
    }


    /**
     * метод добавления нового пользователя
     */
    @Override
    public User createUser(User user) {
        user.setId(nextId++);
        userMap.put(user.getId(), user);
        log.info("user успешно добавлен");
        return userMap.get(user.getId());
    }


    /**
     * метод реализующий обновление существующего пользователя
     */
    @Override
    public User updateUsers(User user) {
        if (!userMap.keySet().contains(user.getId())) {
            throw new NotFoundException("пользователя с id = " + user.getId() + " не существует");
        }
        for (Integer integer : userMap.keySet()) {
            if (integer.equals(user.getId())) {
                userMap.put(integer, user);
                log.info("user успешно обновлен");
            }
        }
        return userMap.get(user.getId());
    }
}
