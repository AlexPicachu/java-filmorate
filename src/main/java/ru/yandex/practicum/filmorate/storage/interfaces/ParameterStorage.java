package ru.yandex.practicum.filmorate.storage.interfaces;

import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.MPA;

import java.util.List;

/**
 * контракт для реализации ParameterDbStorage
 */
public interface ParameterStorage {

    /**
     * список жанров
     */
    List<Genre> getListGenre();

    /**
     * жанр по id
     */
    Genre getGenreById(int id);

    /**
     * список рейтингов
     */
    List<MPA> getListMpa();

    /**
     * рейтинг по id
     */
    MPA getMpaById(int id);
}
