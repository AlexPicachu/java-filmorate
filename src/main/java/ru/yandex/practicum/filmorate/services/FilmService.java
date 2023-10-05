package ru.yandex.practicum.filmorate.services;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validationException.ValidationException;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class FilmService {


    private final Map<Integer, Film> filmMap = new HashMap<>();
    private int nextId = 1;
    private final LocalDate startOfReleaseDate = LocalDate.of(1895, 12, 28);

    public Map<Integer, Film> getFilmMap() {
        return filmMap;
    }

    public Film createFilm(Film film) {
        if (film.getName().isBlank()) {
            throw new ValidationException("название фильма отсутствует");
        }
        if (film.getDescription().length() > 200) {
            throw new ValidationException("описание превышает 200 символов");
        }
        if (film.getReleaseDate().isBefore(startOfReleaseDate)) {
            throw new ValidationException("дата релиза раньше 28 декабря 1895 года");
        }
        if (film.getDuration() <= 0) {
            throw new ValidationException("продолжительность фильма меньше или равна нулю");
        }
        film.setId(nextId++);
        filmMap.put(film.getId(), film);
        log.info("фильм успешно добавлен");
        return film;
    }

    public Film updateFilms(Film film) {
        if (!filmMap.keySet().contains(film.getId())) {
            throw new ValidationException("фильма с id = " + film.getId() + " не существует");
        }
        for (Integer integer : filmMap.keySet()) {
            if (integer.equals(film.getId())) {
                filmMap.put(integer, film);
                log.info("фильм успешно обновлен");
            }
        }
        return film;
    }
}
