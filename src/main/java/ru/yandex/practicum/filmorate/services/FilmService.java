package ru.yandex.practicum.filmorate.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;
import ru.yandex.practicum.filmorate.validationException.ValidationException;

import java.time.LocalDate;
import java.util.*;

// класс для обработки логики запросов из FilmController
@Slf4j
@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmStorage filmStorage;
    private final UserStorage userStorage;


    // получение всех фильмов
    public List<Film> getFilmMap() {
        return filmStorage.getFilmMap();
    }

    //добавление фильмов
    public Film createFilm(Film film) {
        return filmStorage.createFilm(film);
    }

    //обновление фильмов
    public Film updateFilms(Film film) {
        return filmStorage.updateFilms(film);
    }


    public void addLike(int id, int userId) {
    //  User user = userStorage.getUserById(userId);
      Film film = filmStorage.getFilmById(id);
     Set<Integer> integers = film.getLikes();
     integers.add(userId);
     film.setLikes(integers);
    }
    public void deleteLike(int id, int userId){
        Film film = filmStorage.getFilmById(id);
        Set<Integer> integers = film.getLikes();
        integers.remove(userId);
        film.setLikes(integers);
    }
}
