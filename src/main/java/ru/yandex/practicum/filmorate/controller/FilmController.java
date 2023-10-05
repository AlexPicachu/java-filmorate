package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.services.FilmService;

import java.util.Collection;

@RestController
public class FilmController {
    private final FilmService filmService = new FilmService();


    @GetMapping("/films")
    public Collection<Film> getFilms() {
        return filmService.getFilmMap().values();
    }

    @PostMapping("/films")
    public Film addFilm(@RequestBody Film film) {
        return filmService.createFilm(film);
    }

    @PutMapping("/films")
    public Film updateFilm(@RequestBody Film film) {
        return filmService.updateFilms(film);
    }
}
