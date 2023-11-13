package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserDbStorageTest {

    private final UserDbStorage userStorage;

    @BeforeEach
    void fullDB() {
        User user = new User("mail@mail.ru", "Nick Name", "User1", LocalDate.of(1946, 8, 20));
        userStorage.createUser(user);
    }

    @Test
    void testCreateUser() {
        User user = new User("mail@mail.ru", "Nick Name", "User", LocalDate.of(1946, 8, 20));
        userStorage.createUser(user);
        assertThat(user).hasFieldOrPropertyWithValue("id", 2);
        assertThat(user).hasFieldOrPropertyWithValue("name", "User");
    }
}