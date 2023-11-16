package ru.yandex.practicum.filmorate.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.MPA;
import ru.yandex.practicum.filmorate.storage.interfaces.ParameterStorage;
import ru.yandex.practicum.filmorate.validationException.NotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * класс обрабатывающий логику работы с БД
 */
@Primary
@Slf4j
@Component
@RequiredArgsConstructor
public class ParameterDbStorage implements ParameterStorage {
    private final JdbcTemplate jdbcTemplate;

    /**
     * метод возвращающий список жанров
     */
    @Override
    public List<Genre> getListGenre() {
        String sqlQuery = "select * from genre";
        return jdbcTemplate.query(sqlQuery, this::rowGenre);
    }

    /**
     * метод возвращающий жанр по id
     */
    @Override
    public Genre getGenreById(int id) {
        String sqlQuery = "select * from genre where genre_id = ?";
        SqlRowSet userRows = jdbcTemplate.queryForRowSet(sqlQuery, id);
        if (userRows.next()) {
            log.info("Найден жанр с идентификатором: {}", userRows.getString("genre_id"));
            return jdbcTemplate.queryForObject(sqlQuery, this::rowGenre, id);
        } else {
            log.warn("Жанр с идентификатором {} не найден.", id);
            throw new NotFoundException("Такого жанра не существует");
        }
    }

    /**
     * метод возвращающий список рейтингов
     */
    @Override
    public List<MPA> getListMpa() {
        String sqlQuery = "select * from MPA";
        return jdbcTemplate.query(sqlQuery, this::rowMpa);
    }

    /**
     * метод возвращающий рейтинг по id
     */
    @Override
    public MPA getMpaById(int id) {
        String sql = "select * from MPA where MPA_id = ?";
        SqlRowSet userRows = jdbcTemplate.queryForRowSet(sql, id);
        if (userRows.next()) {
            log.info("Найден рейтинг с идентификатором: {}", userRows.getString("MPA_id"));
            return jdbcTemplate.queryForObject(sql, this::rowMpa, id);
        } else {
            log.warn("Жанр с рейтингом {} не найден.", id);
            throw new NotFoundException("Такого рейтинга не существует");

        }
    }

    /**
     * вспомогательный метод
     */
    public Genre rowGenre(ResultSet rs, int i) throws SQLException {
        return Genre.builder()
                .id(rs.getInt("genre_id"))
                .name(rs.getString("name"))
                .build();

    }

    /**
     * вспомогательный метод
     */
    public MPA rowMpa(ResultSet rs, int i) throws SQLException {
        return MPA.builder()
                .id(rs.getInt("MPA_id"))
                .name(rs.getString("MPA_name"))
                .build();
    }
}
