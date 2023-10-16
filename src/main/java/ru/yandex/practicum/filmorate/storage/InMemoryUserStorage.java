package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validationException.ValidationException;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserStorage implements UserStorage{
    private final Map<Integer, User> userMap = new HashMap<>();


    @Override
    public User getUserById(int id) {
        if (!userMap.containsKey(id)){
            throw new ValidationException("Пользователя с таким id " + id + " не существует");
        }
        return userMap.get(id);
    }
}
