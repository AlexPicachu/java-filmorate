package ru.yandex.practicum.filmorate.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserDbStorage;
import ru.yandex.practicum.filmorate.storage.interfaces.UserStorage;

import java.util.List;


/**
 * класс обработки логики запросов приходящих с UserController
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserStorage userStorage;
    private final UserDbStorage userDbStorage;

    /**
     * метод получение всех пользователей
     */
    public List<User> getUserMap() {
        return userStorage.getUserMap();
    }

    /**
     * метод добавление пользователей
     */
    public User createUser(User user) {
        return userStorage.createUser(user);
    }

    /**
     * метод обновление пользователей
     */
    public User updateUsers(User user) {
        return userStorage.updateUsers(user);
    }

    /**
     * метод возвращающий пользователя по id
     */
    public User getUserById(int id) {
        return userStorage.getUserById(id);
    }

    /**
     * метод добавление в друзья
     * id идентификатор пользователя
     * friendId - идентификатор добавляемого в друзья пользователя
     */
    public void addFriends(int id, int friendId) {
        userDbStorage.addFriend(id, friendId);
    }

    /**
     * метод удаление из друзей
     * id - идентификатор пользователя
     * friendId - идентификатор удаляемого из друзей пользователя
     */
    public void deleteFriends(int id, int friendId) {
        userDbStorage.deleteFriend(id, friendId);
    }

    /**
     * метод возвращающий друзей пользователя по id
     */
    public List<User> getFriends(int id) {
        return userDbStorage.printFriend(id);
    }

    /**
     * метод возвращающий общих друзей с другом пользователем
     * id - идентификатор первого пользователя
     * otherId - идентификатор пользователя с которым нужно найти общих друзей
     */
    public List<User> getFriendOfFriends(int id, int otherId) {
        return userDbStorage.getFriendOfFriends(id, otherId);
    }


}
