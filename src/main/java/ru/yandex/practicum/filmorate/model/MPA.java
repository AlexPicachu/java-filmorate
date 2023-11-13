package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * модель рейтинга
 */
@Data
@EqualsAndHashCode(of = "id")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class MPA {

    /**
     * идентификатор рейтинга
     */
    private final int id;

    /**
     * имя рейтинга
     */
    @Builder.Default
    private String name = "";
}
