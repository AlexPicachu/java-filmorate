package ru.yandex.practicum.filmorate.storage.interfaces;

import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.MPA;

import java.util.List;

public interface ParameterStorage {
    List<Genre> getListGenre();
    Genre getGenreById(int id);
    List<MPA> getListMpa();
    MPA getMpaById(int id);
}
