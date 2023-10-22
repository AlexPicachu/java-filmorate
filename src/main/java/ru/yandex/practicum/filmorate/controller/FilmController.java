package ru.yandex.practicum.filmorate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.services.FilmService;
import ru.yandex.practicum.filmorate.validationException.NotFoundException;
import ru.yandex.practicum.filmorate.validationException.ValidationException;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


/**
 * класс контроллер для обработки фильмов
 */
@RestController
public class FilmController {
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }


    /**
     * метод получения всех фильмов
     */
    @GetMapping("/films")
    public Collection<Film> getFilms() {
        return filmService.getFilmMap();
    }


    /**
     * метод добавления фильмов
     */
    @PostMapping("/films")
    public Film addFilm(@RequestBody Film film) {
        if (film.getName().isBlank()) {
            throw new ValidationException("название фильма  отсутствует");
        }
        if (film.getDescription().length() > 200) {

            throw new ValidationException("описание превышает 200 символов");

        }
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException("дата релиза раньше 28 декабря 1895 года");
        }
        if (film.getDuration() <= 0) {
            throw new ValidationException("продолжительность фильма меньше или равна нулю");
        }
        return filmService.createFilm(film);
    }


    /**
     * метод обновления фильмов
     */
    @PutMapping("/films")
    public Film updateFilm(@RequestBody Film film) {
        return filmService.updateFilms(film);
    }


    /**
     * метод добавления лайка
     */
    @PutMapping("/films/{id}/like/{userId}")
    public void addLike(@PathVariable int id, @PathVariable int userId) {
        if (userId <= 0 || id <= 0) {
            throw new NotFoundException("передан некорректный id; " + id +
                    " фильма, или пользователя userId; " + userId);
        }
        filmService.addLike(id, userId);
    }


    /**
     * метод удаления лайка
     */
    @DeleteMapping("/films/{id}/like/{userId}")
    public void deleteLike(@PathVariable int id, @PathVariable int userId) {
        if (userId <= 0 || id <= 0) {
            throw new NotFoundException("передан некорректный id; " + id +
                    " фильма, или пользователя userId; " + userId);
        }
        filmService.deleteLike(id, userId);
    }


    /**
     * метод возвращающий ТОП 10 фильмов
     */
    @GetMapping("/films/popular")
    public List<Film> topLikesFilms(@RequestParam(defaultValue = "10") int count) {
        return filmService.topLikeFilms(count);
    }


    /**
     * метод возвращающий фильм по id
     */
    @GetMapping("/films/{id}")
    public Film getFilmById(@PathVariable int id) {
        return filmService.getFilmById(id);
    }
}
