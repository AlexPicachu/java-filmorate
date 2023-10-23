package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;


/**
 * контракт для реализации класса InMemoryFilmStorage
 */
public interface FilmStorage {
    /**
     * метод возвращающий все фильмы
     */
    List<Film> getFilmMap();

    /**
     * метод добавления фильмов
     */
    Film createFilm(Film film);

    /**
     * метод обновления фильмов
     */
    Film updateFilms(Film film);

    /**
     * метод возвращающий фильм по id
     */
    Film getFilmById(int id);
}
