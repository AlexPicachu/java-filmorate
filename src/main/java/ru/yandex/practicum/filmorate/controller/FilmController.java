package ru.yandex.practicum.filmorate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.services.FilmService;

import java.util.Collection;

// класс контроллер для обработки фильмов
@RestController
public class FilmController {
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    // метод получения всех фильмов
    @GetMapping("/films")
    public Collection<Film> getFilms() {
        return filmService.getFilmMap();
    }

    // метод добавления фильмов
    @PostMapping("/films")
    public Film addFilm(@RequestBody Film film) {
        return filmService.createFilm(film);
    }

    // метод обновления фильмов
    @PutMapping("/films")
    public Film updateFilm(@RequestBody Film film) {
        return filmService.updateFilms(film);
    }
}
