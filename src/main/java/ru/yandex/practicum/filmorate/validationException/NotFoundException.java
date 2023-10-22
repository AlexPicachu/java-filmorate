package ru.yandex.practicum.filmorate.validationException;

/**
 * класс обработки исключений
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
