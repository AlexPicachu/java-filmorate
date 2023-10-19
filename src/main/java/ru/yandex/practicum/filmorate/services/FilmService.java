package ru.yandex.practicum.filmorate.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.validationException.NotFoundException;


import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.compare;

// класс для обработки логики запросов из FilmController
@Slf4j
@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmStorage filmStorage;


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

    //поставить лайк
    public void addLike(int id, int userId) {
        Film film = filmStorage.getFilmById(id);
        Set<Integer> integers = film.getLikes();
        if (userId <= 0) {
            throw new NotFoundException("Пользователя с таким id " + userId + " не существует");
        } else {
            integers.add(userId);
            log.info("like успешно добавлен");
            film.setLikes(integers);
        }
    }

    //удалить лайк
    public void deleteLike(int id, int userId) {
        Film film = filmStorage.getFilmById(id);
        Set<Integer> integers = film.getLikes();
        if (userId <= 0) {
            throw new NotFoundException("Пользователя с таким id " + userId + " не существует");
        }
        integers.remove(userId);
        log.info("like успешно удален");
        film.setLikes(integers);
    }

    //топ фильмов
    public List<Film> topLikeFilms(int count) {
        List<Film> list = getFilmMap();
        if (count > list.size()) {
            count = list.size();
        }
        log.info("топ фильмов {}", list);
        return list.stream()
                .sorted((p0, p1) -> {
                    int comp = compare(p0.getLikes().size(), p1.getLikes().size());
                    return -1 * comp;
                }).limit(count)
                .collect(Collectors.toList());


    }

    //фильм по id
    public Film getFilmById(int id) {
        return filmStorage.getFilmById(id);
    }
}
