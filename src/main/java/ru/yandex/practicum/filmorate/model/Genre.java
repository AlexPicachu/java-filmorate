package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * модель жанра
 */
@Data
@EqualsAndHashCode(of = "id")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Genre {

    /**
     * идентификатор жанра
     */
    private final int id;

    /**
     * имя жанра
     */
    private String name;

}
