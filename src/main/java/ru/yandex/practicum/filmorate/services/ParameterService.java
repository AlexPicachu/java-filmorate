package ru.yandex.practicum.filmorate.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.MPA;
import ru.yandex.practicum.filmorate.storage.interfaces.ParameterStorage;

import java.util.List;

/**
 * класс обработки запросов из ParameterController
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ParameterService {
    private final ParameterStorage parameterStorage;

    /**
     * метод возвращающий список жанров
     */
    public List<Genre> getListGenre() {
        return parameterStorage.getListGenre();
    }

    /**
     * метод возвращающий жанр по id
     */
    public Genre getGenreById(int id) {
        return parameterStorage.getGenreById(id);
    }

    /**
     * метод возвращающий список рейтингов
     */
    public List<MPA> getListMpa() {
        return parameterStorage.getListMpa();
    }

    /**
     * метод возвращающий рейтинг по id
     */
    public MPA getMpaById(int id) {
        return parameterStorage.getMpaById(id);
    }

}
