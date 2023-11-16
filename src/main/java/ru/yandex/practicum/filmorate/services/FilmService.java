package ru.yandex.practicum.filmorate.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmDbStorage;
import ru.yandex.practicum.filmorate.storage.interfaces.FilmStorage;

import java.util.*;


/**
 * класс для обработки логики запросов из FilmController
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmStorage filmStorage;
    private final FilmDbStorage filmDbStorage;


    /**
     * метод получение всех фильмов
     */
    public List<Film> getFilmMap() {
        return filmStorage.getFilmMap();
    }

    /**
     * метод добавление фильмов
     */
    public Film createFilm(Film film) {
        return filmStorage.createFilm(film);
    }

    /**
     * метод обновление фильмов
     */
    public Film updateFilms(Film film) {
        return filmStorage.updateFilms(film);
    }

    /**
     * метод возвращающий фильм по id
     */
    public Film getFilmById(int id) {
        return filmStorage.getFilmById(id);
    }

    /**
     * метод поставить лайк
     * id идентификатор фильма
     * userId идентификатор пользователя поставившего лайк
     */
    public void addLike(int id, int userId) {
        filmDbStorage.addLike(id, userId);

    }

    /**
     * метод удалить лайк
     * id - идентификатор фильма
     * userId - идентификатор пользователя, лайк которого нужно удалить
     */
    public void deleteLike(int id, int userId) {
        filmDbStorage.deleteLike(id, userId);
    }

    /**
     * метод возвращающий ТОП фильмов по лайкам пользователей
     * count - задано как defaultValue = 10
     */
    public List<Film> topLikeFilms(int count) {
        return filmDbStorage.topLikesFilms(count);
    }

}
