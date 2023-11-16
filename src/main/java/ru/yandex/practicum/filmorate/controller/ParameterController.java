package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.MPA;
import ru.yandex.practicum.filmorate.services.ParameterService;

import java.util.ArrayList;
import java.util.List;

/**
 * класс контроллер для обработки запросов к жанрам и рейтингам
 */
@Slf4j
@RestController
public class ParameterController {
    ParameterService parameterService;

    @Autowired
    public ParameterController(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    /**
     * метод возвращающий список жанров
     */
    @GetMapping("/genres")
    public List<Genre> getListGenre() {
        return new ArrayList<Genre>(parameterService.getListGenre());
    }

    /**
     * метод возвращающий жанр по id
     */
    @GetMapping("/genres/{id}")
    public Genre getGenreById(@PathVariable int id) {
        return parameterService.getGenreById(id);
    }

    /**
     * метод возвращающий список рейтингов
     */
    @GetMapping("/mpa")
    public List<MPA> getListMpa() {
        return new ArrayList<MPA>(parameterService.getListMpa());
    }

    /**
     * метод возвращающий рейтинг по id
     */
    @GetMapping("/mpa/{id}")
    public MPA getMpaById(@PathVariable int id) {
        return parameterService.getMpaById(id);
    }
}
