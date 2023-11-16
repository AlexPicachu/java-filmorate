package ru.yandex.practicum.filmorate.model;

import lombok.Data;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * модель пользователя
 */
@Data
public class User {

    /**
     * целочисленный идентификатор
     */
    private int id;

    /**
     * электронная почта
     */
    @NotBlank
    @Email
    private String email;

    /**
     * логин пользователя
     */
    @NotBlank
    private String login;

    /**
     * имя для отображения
     */
    private String name;

    /**
     * дата рождения
     */
    @PastOrPresent
    @NotNull
    private LocalDate birthday;

    /**
     * Set для хранения id добавленных друзей
     */
    private Set<Integer> friendsId = new HashSet<>();

    public Set<Integer> applications = new HashSet<>();

    public User() {
    }

    public User(int id, String email, String login, String name, LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.name = name;
        this.birthday = birthday;
    }

    public User(String email, String login, String name, LocalDate birthday) {
        this.email = email;
        this.login = login;
        this.name = name;
        this.birthday = birthday;
    }

    public User(String email, String login, LocalDate birthday) {
        this.email = email;
        this.login = login;
        this.birthday = birthday;
    }


}
