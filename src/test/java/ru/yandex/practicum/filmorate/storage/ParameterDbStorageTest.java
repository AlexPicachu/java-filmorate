package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.MPA;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * класс для тестирования ParameterDbStorage
 */
@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ParameterDbStorageTest {

    private final ParameterDbStorage parameterDbStorage;

    /**
     * список рейтингов
     */
    @Test
    void testGetListMpa() {
        List<MPA> mpa = parameterDbStorage.getListMpa();
        assertEquals(5, mpa.size());
        assertEquals("G", mpa.get(0).getName());
        assertEquals(1, mpa.get(0).getId());
    }

    /**
     * рейтинг по id
     */
    @Test
    void testGetByIdMpa() {
        MPA mpa = parameterDbStorage.getMpaById(2);
        assertEquals(2, mpa.getId());
        assertEquals("PG", mpa.getName());
    }

    /**
     * список жанров
     */
    @Test
    void testGetListGenres() {
        List<Genre> genres = parameterDbStorage.getListGenre();
        assertEquals(6, genres.size());
        assertEquals("Комедия", genres.get(0).getName());
        assertEquals(1, genres.get(0).getId());
    }

    /**
     * жанр по id
     */
    @Test
    void testGetByIdGenre() {
        Genre genre = parameterDbStorage.getGenreById(2);
        assertEquals(2, genre.getId());
        assertEquals("Драма", genre.getName());

    }


}
