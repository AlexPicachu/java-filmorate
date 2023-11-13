package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.interfaces.FilmStorage;
import ru.yandex.practicum.filmorate.validationException.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * класс хранилище фильмов
 */
@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Integer, Film> filmMap = new HashMap<>();
    private int nextId = 1;


    /**
     * метод реализующий возврат списка фильмов
     */
    @Override
    public List<Film> getFilmMap() {
        return new ArrayList<>(filmMap.values());
    }


    /**
     * метод реализующий добавление нового фильма
     */
    @Override
    public Film createFilm(Film film) {
        film.setId(nextId++);
        filmMap.put(film.getId(), film);
        log.info("фильм успешно добавлен");
        return film;
    }


    /**
     * метод обновляющий существующий фильм
     */
    @Override
    public Film updateFilms(Film film) {
        if (!filmMap.keySet().contains(film.getId())) {
            throw new NotFoundException("Фильма с таким id = " + film.getId() + " не существует");
        }
        for (Integer integer : filmMap.keySet()) {
            if (integer.equals(film.getId())) {
                filmMap.put(integer, film);
                log.info("фильм успешно обновлен");
            }
        }
        return film;
    }


    /**
     * метод возвращающий фильм по его id
     */
    @Override
    public Film getFilmById(int id) {
        if (!filmMap.containsKey(id)) {
            throw new NotFoundException("Фильма с таким id " + id + " не существует");
        }
        return filmMap.get(id);
    }


}
