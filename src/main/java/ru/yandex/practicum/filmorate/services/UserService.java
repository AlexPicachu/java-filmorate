package ru.yandex.practicum.filmorate.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;
import ru.yandex.practicum.filmorate.validationException.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


// класс обработки логики запросов приходящих с UserController
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserStorage userStorage;


    //получение всех пользователей
    public List<User> getUserMap() {
        return userStorage.getUserMap();
    }

    //добавление пользователей
    public User createUser(User user) {
        return userStorage.createUser(user);
    }

    //обновление пользователей
    public User updateUsers(User user) {
        return userStorage.updateUsers(user);
    }

    //добавление в друзья
    public void addFriends(int id, int friendId) {
        User user = userStorage.getUserById(id);
        User user1 = userStorage.getUserById(friendId);
        Set<Integer> integerSet = user.getFriendsId();
        Set<Integer> integerSet1 = user1.getFriendsId();
        if (friendId <= 0 || id <= 0){
            throw new NotFoundException("Пользователя с таким id " + id + " не существует");
        }
        integerSet.add(friendId);
        integerSet1.add(id);
        log.info("друзья успешно добавлен");
        user.setFriendsId(integerSet);
        user1.setFriendsId(integerSet1);
    }

    public void deleteFriends(int id, int friendId) {
        User user = userStorage.getUserById(id);
        Set<Integer> integerSet = user.getFriendsId();
        integerSet.remove(friendId);
        log.info("друг с id " + friendId + " успешно удален");
        user.setFriendsId(integerSet);
    }

    public List<User> getFriends(int id) {
        User user = userStorage.getUserById(id);
        Set<Integer> integerSet = user.getFriendsId();
        List<User> getFriend = new ArrayList<>();
        for (Integer integer : integerSet) {
            getFriend.add(userStorage.getUserById(integer));
        }
        return getFriend;
    }

    public List<User> getFriendOfFriends(int id, int otherId) {
return getFriends(id).stream()
        .filter(getFriends(otherId)::contains)
        .collect(Collectors.toList());

    }

    public User getUserById(int id) {
       return userStorage.getUserById(id);
    }
}
