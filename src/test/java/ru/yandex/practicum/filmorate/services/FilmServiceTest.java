package ru.yandex.practicum.filmorate.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FilmDbStorage;
import ru.yandex.practicum.filmorate.storage.interfaces.FilmStorage;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.validationException.NotFoundException;
import ru.yandex.practicum.filmorate.validationException.ValidationException;

import java.time.LocalDate;
import java.util.Collection;


import static org.junit.jupiter.api.Assertions.*;


/**
 * тестовый класс для проверки FilmController
 */
class FilmServiceTest {
    FilmStorage filmStorage;
    FilmDbStorage filmDbStorage;
    JdbcTemplate jdbcTemplate;
    FilmController filmController;
    FilmService filmService;
    Film film;
    User user;


    @BeforeEach
    public void launchBefore() {
        filmStorage = new InMemoryFilmStorage();
        jdbcTemplate = new JdbcTemplate();
        filmDbStorage = new FilmDbStorage(jdbcTemplate);
        filmService = new FilmService(filmStorage, filmDbStorage);
        filmController = new FilmController(filmService);
        film = new Film("поехали", "интересно",
                LocalDate.of(2009, 11, 5), 100);
        user = new User("qwe@mail.ru", "qwerty", "Ivan",
                LocalDate.of(1990, 10, 3));
    }


    /**
     * проверяем метод getFilms, возврата фильмов
     */
    @Test
    public void getOfFilmTest() {
        filmController.addFilm(film);
        Collection<Film> filmList = filmController.getFilms();
        assertEquals(1, filmList.size(), "фильмов храниться больше");
    }


    /**
     * проверяем метод addFilm, добавления фильма, с положительным сценарием
     */
    @Test
    public void addFilmTestPositive() {
        Film film1 = filmController.addFilm(film);
        assertEquals("поехали", film1.getName(), "фильм не добавлен");
    }


    /**
     * проверяем работу метода addFilm, с граничными значениями
     */
    @Test
    public void addFilmTestLimitValues() {
        Film film1 = filmController.addFilm(new Film("поехали", "интересно",
                LocalDate.of(1895, 12, 28), 1));
        assertEquals(1, film1.getDuration(), "фильм не добавлен");
    }


    /**
     * проверяем работу метода addFilm, при работе с исключениями(негативный тест)
     */
    @Test
    public void addFilmTestNegative() {
        Film film1 = new Film("поехали", "интересно",
                LocalDate.of(1895, 12, 28), 0);
        ValidationException ex = Assertions.assertThrows(
                ValidationException.class,
                () -> filmController.addFilm(film1));
        Assertions.assertEquals("продолжительность фильма меньше или равна нулю", ex.getMessage());
    }


    /**
     * проверяем работу метода updateFilm, обновление фильма
     */
    @Test
    public void updateTestFilm() {
        filmController.addFilm(film);
        film = new Film(1, "полетели, ф не поехали", "интересно",
                LocalDate.of(2009, 11, 5), 100);
        Film film1 = filmController.updateFilm(film);
        assertEquals("полетели, ф не поехали", film1.getName(), "фильм не обновлен");
    }


    /**
     * проверяем работу метода updateFilm при негативном сценарии
     */
    @Test
    public void updateTestNegative() {
        filmController.addFilm(film);
        film = new Film(999, "полетели, ф не поехали", "интересно",
                LocalDate.of(2009, 11, 5), 100);
        NotFoundException ex = assertThrows(NotFoundException.class, () -> filmController.updateFilm(film));
        Assertions.assertEquals("Фильма с таким id = " + film.getId() + " не существует", ex.getMessage());
    }


    /**
     * проверяем работу метода getFilmById
     */
    @Test
    public void getFilmByIdTest() {
        Film film1 = new Film("фильм", "интересный",
                LocalDate.of(2009, 11, 5), 100);
        filmController.addFilm(film1);
        filmController.addFilm(film);
        Collection<Film> filmList = filmController.getFilms();
        assertFalse(filmList.isEmpty());
        assertEquals(2, filmList.size(), "фильмы с id 1 и 2 не добавлены");
        Film filmById = filmController.getFilmById(film1.getId());
        assertEquals(1, filmById.getId(), "фильм по id не вернулся");
    }

}

