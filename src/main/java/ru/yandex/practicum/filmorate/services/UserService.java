package ru.yandex.practicum.filmorate.services;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validationException.ValidationException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserService {
    private final Map<Integer, User> userMap = new HashMap<>();
    private int nextId = 1;
    private final LocalDate presentTime = LocalDate.now();

    public Map<Integer, User> getUserMap() {
        return userMap;
    }


    public User createUser(User user) {
        if (user.getEmail().isEmpty() || !user.getEmail().contains("@")) {
            throw new ValidationException("электронная почта не заполнена или не содержит @ " + user.getEmail());
        }
        if (user.getLogin().isEmpty() || user.getLogin().contains(" ")) {
            throw new ValidationException("логин пустой или содержит пробелы");
        }
        if (user.getBirthday().isAfter(presentTime)) {
            throw new ValidationException("дата рождения находится в будущем");
        }
        if (user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        user.setId(nextId++);
        userMap.put(user.getId(), user);
        log.info("user успешно добавлен");
        return userMap.get(user.getId());
    }


    public User updateUsers(User user) {
        if (!userMap.keySet().contains(user.getId())) {
            throw new ValidationException("пользователя с id = " + user.getId() + " не существует");
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
