package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validationException.NotFoundException;
import ru.yandex.practicum.filmorate.validationException.ValidationException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//класс хранилище пользователей
@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage {
    private final Map<Integer, User> userMap = new HashMap<>();
    private int nextId = 1;
    private final LocalDate presentTime = LocalDate.now();

    //покажи пользователя по id
    @Override
    public User getUserById(int id) {
        if (!userMap.containsKey(id)) {
            throw new NotFoundException("Пользователя с таким id " + id + " не существует");
        }
        return userMap.get(id);
    }

    //верни список пользователей
    @Override
    public List<User> getUserMap() {
        return new ArrayList<>(userMap.values());
    }

    //положи нового пользователя
    @Override
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
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        user.setId(nextId++);
        userMap.put(user.getId(), user);
        log.info("user успешно добавлен");
        return userMap.get(user.getId());
    }

    //обнови пользователя
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
